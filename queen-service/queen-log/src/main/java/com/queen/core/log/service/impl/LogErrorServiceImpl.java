package com.queen.core.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.queen.core.log.mapper.LogErrorMapper;
import com.queen.core.log.service.ILogErrorService;
import com.queen.core.log.model.LogError;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author jensen
 * @since 2018-09-26
 */
@Service
public class LogErrorServiceImpl extends ServiceImpl<LogErrorMapper, LogError> implements ILogErrorService {

}
