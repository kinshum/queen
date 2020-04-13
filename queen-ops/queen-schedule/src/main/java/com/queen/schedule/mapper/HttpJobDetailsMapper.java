package com.queen.schedule.mapper;

import com.queen.schedule.entity.HttpJobDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HttpJobDetailsMapper {

    HttpJobDetail selectByJobNameAndJobGroup(@Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    int insertSelective(HttpJobDetail httpJobDetails);

    List<HttpJobDetail> selectHttpJobs(Map<String, Object> map);

    Integer selectHttpJobsCount(Map<String, Object> map);

    List<HttpJobDetail> selectHistoryHttpJobs(Map<String, Object> map);

    Integer selectHistoryHttpJobsCount(Map<String, Object> map);

}
