package com.queen.schedule.service.impl;

import com.alibaba.fastjson.JSON;
import com.queen.common.constant.CommonConstant;
import com.queen.core.tool.jackson.JsonUtil;
import com.queen.schedule.config.quartz.JobUtil;
import com.queen.schedule.entity.AddHttpJobParam;
import com.queen.schedule.entity.HttpJobDetail;
import com.queen.schedule.entity.HttpJobLogs;
import com.queen.schedule.entity.Page;
import com.queen.schedule.job.HttpGetJob;
import com.queen.schedule.job.HttpPostFormDataJob;
import com.queen.schedule.job.HttpPostJsonJob;
import com.queen.schedule.mapper.HttpJobDetailsMapper;
import com.queen.schedule.mapper.HttpJobLogsMapper;
import com.queen.schedule.service.IQuartzService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jensen
 * @description quartz服务
 * @date 2020/1/8 18:08
 */
@Service
public class IQuartzServiceImpl implements IQuartzService {

    private static final Logger logger = LoggerFactory.getLogger(IQuartzServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobUtil jobUtil;

    @Autowired
    private HttpJobLogsMapper httpJobLogsDao;

    @Autowired
    private HttpJobDetailsMapper httpJobDetailsDao;


    @Override
    public void pauseJob(String jobName, String jobGroup) {
        String jobStatusInfo = jobUtil.getJobStatusInfo(jobName, jobGroup);
        if (StringUtils.equals(jobStatusInfo, CommonConstant.JOB_STATUS_PAUSED)) {
            throw new RuntimeException("当前任务已是暂停状态!");
        }
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void resumeJob(String jobName, String jobGroup) {
        String jobStatusInfo = jobUtil.getJobStatusInfo(jobName, jobGroup);
        if (!StringUtils.equals(jobStatusInfo, CommonConstant.JOB_STATUS_PAUSED)) {
            throw new RuntimeException("任务仅在暂停状态时才能恢复!");
        }
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteJob(String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        TriggerKey triggerKey = jobUtil.getTriggerKeyByJob(jobName, jobGroup);
        try {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCronExpression(String jobName, String jobGroup, String cronExpression) {
        TriggerKey triggerKey = jobUtil.getTriggerKeyByJob(jobName, jobGroup);

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression重新构建trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(scheduleBuilder).build();
        try {
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addHttpJob(AddHttpJobParam addHttpJobParam) {
        String jobName = addHttpJobParam.getJobName();
        String jobGroup = addHttpJobParam.getJobGroup();
        HttpJobDetail httpJobDetails = httpJobDetailsDao.selectByJobNameAndJobGroup(jobName, jobGroup);
        if (httpJobDetails != null) {
            //通过jobName和jobGroup确保任务的唯一性
            throw new RuntimeException("任务名称重复!");
        }

        String requestType = addHttpJobParam.getRequestType();
        String description = addHttpJobParam.getDescription();
        String cronExpression = addHttpJobParam.getCronExpression();
        String url = addHttpJobParam.getUrl();
        String jsonParamsStr = addHttpJobParam.getParams();

        httpJobDetails = new HttpJobDetail();
        httpJobDetails.setJobName(jobName);
        httpJobDetails.setJobGroup(jobGroup);
        httpJobDetails.setDescription(description);
        httpJobDetails.setRequestType(requestType);
        httpJobDetails.setHttpUrl(url);
        if (!JsonUtil.isJson(jsonParamsStr)) {
            throw new RuntimeException("参数不是JSON格式");
        }

        Map<String, Object> jobParamsMap = new HashMap<>();
        jobParamsMap.put(CommonConstant.URL, url);
        jobParamsMap.put(CommonConstant.PARAMS, jsonParamsStr);

        JobDetail jobDetail = null;
        //根据不同类型的job构建job信息
        switch (requestType) {
            //postJson
            case CommonConstant.POST_JSON:
                jobDetail = JobBuilder.newJob(HttpPostJsonJob.class)
                        .withIdentity(jobName, jobGroup)
                        .build();

                //jsonStr的参数直接用
                if (StringUtils.isNotEmpty(jsonParamsStr)) {
                    httpJobDetails.setHttpParams(jsonParamsStr);
                }
                break;

            //postFormData
            case CommonConstant.POST_FORM_DATA:
                jobDetail = JobBuilder.newJob(HttpPostFormDataJob.class)
                        .withIdentity(jobName, jobGroup)
                        .build();

                //jsonStr参数转为formData的Map
                Map<String, Object> formDataParamMap;
                if (StringUtils.isEmpty(jsonParamsStr)) {
                    formDataParamMap = null;
                } else {
                    formDataParamMap = JSON.parseObject(jsonParamsStr, Map.class);
                    httpJobDetails.setHttpParams(formDataParamMap.toString());
                }
                jobParamsMap.put(CommonConstant.PARAMS, formDataParamMap);

                break;

            //get
            case CommonConstant.GET:
                jobDetail = JobBuilder.newJob(HttpGetJob.class)
                        .withIdentity(jobName, jobGroup)
                        .build();

                //jsonStr参数转为formData的Map
                Map<String, Object> paramMap;
                if (StringUtils.isEmpty(jsonParamsStr)) {
                    paramMap = null;
                } else {
                    paramMap = JSON.parseObject(jsonParamsStr, Map.class);
                    httpJobDetails.setHttpParams(paramMap.toString());
                }
                jobParamsMap.put(CommonConstant.PARAMS, paramMap);

                break;
        }

        //任务信息
        jobDetail.getJobDataMap().putAll(jobParamsMap);
        jobDetail.getJobDataMap().put(CommonConstant.REQUEST_TYPE, requestType);

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder;
        try {
            scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        } catch (Exception e) {
            throw new RuntimeException("Illegal CronExpression!");
        }

        TriggerKey triggerKey = jobUtil.getTriggerKeyByJob(jobName, jobGroup);

        //构建一个trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .startNow()
                .withSchedule(scheduleBuilder).build();

        try {
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException("Schedule Exception.", e);
        }

        httpJobDetailsDao.insertSelective(httpJobDetails);
        logger.info("Success in addJob, [{}]-[{}]", jobName, jobGroup);


    }

    @Override
    public Page<HttpJobDetail> getHttpJobs(String searchParam, Integer pageSize, Integer pageNum) {
        Integer beginIndex = (pageNum - 1) * pageSize;

        Map<String, Object> sqlMap = new HashMap<>();
        sqlMap.put("searchParam", searchParam);
        sqlMap.put("pageSize", pageSize);
        sqlMap.put("beginIndex", beginIndex);

        List<HttpJobDetail> httpJobDetailVOList = httpJobDetailsDao.selectHttpJobs(sqlMap);

        for (HttpJobDetail httpJobDetailVO : httpJobDetailVOList) {
            //设置jobStatusInfo
            String jobStatusInfo = jobUtil.getJobStatusInfo(httpJobDetailVO.getJobName(), httpJobDetailVO.getJobGroup());
            httpJobDetailVO.setJobStatusInfo(jobStatusInfo);
            //任务状态正常，根据cron表达式计算下次运行时间
            if (StringUtils.equals(jobStatusInfo, CommonConstant.JOB_STATUS_NORMAL)) {
                httpJobDetailVO.setNextFireTime(jobUtil.getNextFireDate(httpJobDetailVO.getCronExpression()));
            }
        }
        Page<HttpJobDetail> httpJobDetailVOPageVO = new Page<>();
        httpJobDetailVOPageVO.setPageNum(pageNum);
        httpJobDetailVOPageVO.setPageSize(pageSize);
        httpJobDetailVOPageVO.setCount(httpJobDetailVOList.size());
        httpJobDetailVOPageVO.setTotalCount(httpJobDetailsDao.selectHttpJobsCount(sqlMap));
        httpJobDetailVOPageVO.setResultList(httpJobDetailVOList);
        return httpJobDetailVOPageVO;
    }

    @Override
    public Page<HttpJobDetail> getHistoryHttpJobs(String searchParam, Integer pageSize, Integer pageNum) {
        Integer beginIndex = (pageNum - 1) * pageSize;

        Map<String, Object> sqlMap = new HashMap<>();
        sqlMap.put("searchParam", searchParam);
        sqlMap.put("pageSize", pageSize);
        sqlMap.put("beginIndex", beginIndex);

        List<HttpJobDetail> httpJobDetailVOList = httpJobDetailsDao.selectHistoryHttpJobs(sqlMap);

        Page<HttpJobDetail> httpJobDetailVOPageVO = new Page<>();
        httpJobDetailVOPageVO.setPageNum(pageNum);
        httpJobDetailVOPageVO.setPageSize(pageSize);
        httpJobDetailVOPageVO.setCount(httpJobDetailVOList.size());
        httpJobDetailVOPageVO.setTotalCount(httpJobDetailsDao.selectHistoryHttpJobsCount(sqlMap));
        httpJobDetailVOPageVO.setResultList(httpJobDetailVOList);

        return httpJobDetailVOPageVO;
    }

    @Override
    public Page<HttpJobLogs> getHttpJobLogs(String jobName, String jobGroup, String searchParam, Integer pageSize, Integer pageNum) {
        Integer beginIndex = (pageNum - 1) * pageSize;

        Map<String, Object> sqlMap = new HashMap<>();
        sqlMap.put("jobName", jobName);
        sqlMap.put("jobGroup", jobGroup);
        sqlMap.put("searchParam", searchParam);
        sqlMap.put("pageSize", pageSize);
        sqlMap.put("beginIndex", beginIndex);

        List<HttpJobLogs> httpJobLogsList = httpJobLogsDao.selectHttpJobLogs(sqlMap);

        Page<HttpJobLogs> httpJobDetailVOPageVO = new Page<>();
        httpJobDetailVOPageVO.setPageNum(pageNum);
        httpJobDetailVOPageVO.setPageSize(pageSize);
        httpJobDetailVOPageVO.setCount(httpJobLogsList.size());
        httpJobDetailVOPageVO.setTotalCount(httpJobLogsDao.selectHttpJobLogsCount(sqlMap));
        httpJobDetailVOPageVO.setResultList(httpJobLogsList);

        return httpJobDetailVOPageVO;
    }
}
