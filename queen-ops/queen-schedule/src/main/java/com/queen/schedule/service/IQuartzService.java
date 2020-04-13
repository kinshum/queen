package com.queen.schedule.service;

import com.queen.schedule.entity.AddHttpJobParam;
import com.queen.schedule.entity.HttpJobDetail;
import com.queen.schedule.entity.HttpJobLogs;
import com.queen.schedule.entity.Page;

/**
 * @author jensen
 * @description quartz服务
 * @date 2020/1/8 18:08
 */
public interface IQuartzService {

    /**
     * 暂停任务
     *
     * @param jobName
     * @param jobGroup
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复任务
     *
     * @param jobName
     * @param jobGroup
     */
    void resumeJob(String jobName, String jobGroup);

    /**
     * 删除任务
     *
     * @param jobName
     * @param jobGroup
     */
    void deleteJob(String jobName, String jobGroup);

    /**
     * 更新任务cron表达式
     *
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     */
    void updateCronExpression(String jobName, String jobGroup, String cronExpression);



    /**
     * 添加http类型job
     *
     * @param addHttpJobParam
     */
    void addHttpJob(AddHttpJobParam addHttpJobParam);

    /**
     * 查看正在进行的http类型job
     *
     * @param searchParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<HttpJobDetail> getHttpJobs(String searchParam, Integer pageSize, Integer pageNum);

    /**
     * 查看历史http类型job
     *
     * @param searchParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<HttpJobDetail> getHistoryHttpJobs(String searchParam, Integer pageSize, Integer pageNum);

    /**
     * 查看http类型的job执行记录
     *
     * @param jobName
     * @param jobGroup
     * @param searchParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<HttpJobLogs> getHttpJobLogs(String jobName, String jobGroup, String searchParam, Integer pageSize, Integer pageNum);

}
