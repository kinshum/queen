package com.queen.schedule.feign;

import com.queen.schedule.entity.AddHttpJobParam;
import com.queen.schedule.entity.HttpJobDetail;
import com.queen.schedule.entity.HttpJobLogs;
import com.queen.schedule.entity.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "queen-schedule", fallback = QuartzClientFallback.class)
public interface IQuartzClient {

    /**
     * 暂停任务
     *
     * @param jobName
     * @param jobGroup
     */
    @GetMapping("/pauseJob")
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复任务
     *
     * @param jobName
     * @param jobGroup
     */
    @GetMapping("/resumeJob")
    void resumeJob(String jobName, String jobGroup);

    /**
     * 删除任务
     *
     * @param jobName
     * @param jobGroup
     */
    @GetMapping("/deleteJob")
    void deleteJob(String jobName, String jobGroup);

    /**
     * 更新任务cron表达式
     *
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     */
    @GetMapping("/updateCronExpression")
    void updateCronExpression(String jobName, String jobGroup, String cronExpression);



    /**
     * 添加http类型job
     *
     * @param addHttpJobParam
     */
    @GetMapping("/addHttpJob")
    void addHttpJob(AddHttpJobParam addHttpJobParam);

    /**
     * 查看正在进行的http类型job
     *
     * @param searchParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/getHttpJobs")
    Page<HttpJobDetail> getHttpJobs(String searchParam, Integer pageSize, Integer pageNum);

    /**
     * 查看历史http类型job
     *
     * @param searchParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/getHistoryHttpJobs")
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
    @GetMapping("/getHttpJobLogs")
    Page<HttpJobLogs> getHttpJobLogs(String jobName, String jobGroup, String searchParam, Integer pageSize, Integer pageNum);



}
