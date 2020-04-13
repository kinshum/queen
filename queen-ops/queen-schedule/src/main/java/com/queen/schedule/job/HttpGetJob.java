package com.queen.schedule.job;


import com.queen.common.constant.CommonConstant;
import com.queen.common.tool.HttpClientUtil;

import com.queen.schedule.entity.HttpJobLogs;

import com.queen.schedule.mapper.HttpJobLogsMapper;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@DisallowConcurrentExecution
public class HttpGetJob implements Job {

    private Logger logger = LoggerFactory.getLogger(HttpGetJob.class);

    @Autowired
    private HttpJobLogsMapper httpJobLogsDao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String jobName = jobDetail.getKey().getName();
        String jobGroup = jobDetail.getKey().getGroup();

        Map<String, Object> jobParamsMap = jobDetail.getJobDataMap();

        String requestType = (String) jobParamsMap.get(CommonConstant.REQUEST_TYPE);
        String url = (String) jobParamsMap.get(CommonConstant.URL);
        Map<String, Object> paramMap = (Map) jobParamsMap.get(CommonConstant.PARAMS);

        HttpJobLogs httpJobLogs = new HttpJobLogs();
        httpJobLogs.setJobName(jobName);
        httpJobLogs.setJobGroup(jobGroup);
        httpJobLogs.setRequestType(requestType);
        httpJobLogs.setHttpUrl(url);
        if (null != paramMap && paramMap.size() > 0) {
            httpJobLogs.setHttpParams(paramMap.toString());
        }
        String result = HttpClientUtil.getMap(url, paramMap);
        httpJobLogs.setResult(result);
        logger.info("Success in execute [{}_{}]", jobName, jobGroup);
        httpJobLogsDao.insertSelective(httpJobLogs);
    }

}
