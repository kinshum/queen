package com.queen.core.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.queen.core.log.service.ILogUsualService;
import com.queen.core.log.mapper.LogUsualMapper;
import com.queen.core.log.model.LogUsual;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author jensen
 * @since 2018-10-12
 */
@Service
public class LogUsualServiceImpl extends ServiceImpl<LogUsualMapper, LogUsual> implements ILogUsualService {

}
