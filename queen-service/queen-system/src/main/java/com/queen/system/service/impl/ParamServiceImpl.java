package com.queen.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.queen.system.entity.Param;
import com.queen.system.service.IParamService;
import com.queen.system.vo.ParamVO;

import com.queen.core.mp.base.BaseServiceImpl;
import com.queen.system.mapper.ParamMapper;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author jensen
 */
@Service
public class ParamServiceImpl extends BaseServiceImpl<ParamMapper, Param> implements IParamService {

	@Override
	public IPage<ParamVO> selectParamPage(IPage<ParamVO> page, ParamVO param) {
		return page.setRecords(baseMapper.selectParamPage(page, param));
	}

}
