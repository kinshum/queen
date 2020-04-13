package com.queen.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.queen.system.entity.Param;
import com.queen.system.vo.ParamVO;

import com.queen.core.mp.base.BaseService;

/**
 * 服务类
 *
 * @author jensen
 */
public interface IParamService extends BaseService<Param> {

	/***
	 * 自定义分页
	 * @param page
	 * @param param
	 * @return
	 */
	IPage<ParamVO> selectParamPage(IPage<ParamVO> page, ParamVO param);

}
