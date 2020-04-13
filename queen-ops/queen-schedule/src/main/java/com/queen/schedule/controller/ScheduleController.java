package com.queen.schedule.controller;

import com.queen.core.tool.api.R;
import com.queen.schedule.entity.AddHttpJobParam;
import com.queen.schedule.service.IQuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jensen
 * @description 调度任务
 * @date 2020/1/10 15:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/schedule")
@Api(value = "调度任务", tags = "调度任务")
public class ScheduleController {

    private IQuartzService iQuartzService;

    @PostMapping("/add")
    @ApiOperation("job任务添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "jobName", value = "任务名称"),
            @ApiImplicitParam(paramType = "query", required = true, name = "jobGroup", value = "任务组"),
            @ApiImplicitParam(paramType = "query", required = true, name = "description", value = "任务描述"),
            @ApiImplicitParam(paramType = "query", required = true, name = "requestType", value = "请求类型"),
            @ApiImplicitParam(paramType = "query", required = true, name = "url", value = "url"),
            @ApiImplicitParam(paramType = "query", name = "params", value = "参数"),
            @ApiImplicitParam(paramType = "query", required = true, name = "cronExpression", value = "cron表达式")
    })
    public R addPostJsonJob(@RequestParam(name = "jobName") String jobName, @RequestParam(name = "jobGroup") String jobGroup,
                            @RequestParam(name = "description") String description, @RequestParam(name = "requestType") String requestType,
                            @RequestParam(name = "url") String url, @RequestParam(name = "params",required = false) String params,
                            @RequestParam(name = "cronExpression") String cronExpression) {
        AddHttpJobParam addHttpJobParam = new AddHttpJobParam();
        addHttpJobParam.setCronExpression(cronExpression);
        addHttpJobParam.setDescription(description);
        addHttpJobParam.setJobGroup(jobGroup);
        addHttpJobParam.setJobName(jobName);
        addHttpJobParam.setParams(params);
        addHttpJobParam.setRequestType(requestType);
        addHttpJobParam.setUrl(url);
        iQuartzService.addHttpJob(addHttpJobParam);
        return R.success("操作成功");
    }









}
