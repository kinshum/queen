package com.queen.core.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.queen.core.log.service.ILogApiService;
import com.queen.core.log.mapper.LogApiMapper;
import com.queen.core.log.model.LogApi;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author jensen
 */
@Service
public class LogApiServiceImpl extends ServiceImpl<LogApiMapper, LogApi> implements ILogApiService {


}
