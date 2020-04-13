package com.queen.system.service.impl;

import com.queen.system.entity.AuthClient;
import com.queen.core.mp.base.BaseServiceImpl;

import com.queen.system.mapper.AuthClientMapper;
import com.queen.system.service.IAuthClientService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author jensen
 */
@Service
public class AuthClientServiceImpl extends BaseServiceImpl<AuthClientMapper, AuthClient> implements IAuthClientService {

}
