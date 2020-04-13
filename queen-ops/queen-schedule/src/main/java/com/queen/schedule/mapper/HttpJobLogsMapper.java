package com.queen.schedule.mapper;


import com.queen.schedule.entity.HttpJobLogs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HttpJobLogsMapper {

    int insertSelective(HttpJobLogs httpJobLogs);

    List<HttpJobLogs> selectHttpJobLogs(Map<String, Object> map);

    Integer selectHttpJobLogsCount(Map<String, Object> map);
}
