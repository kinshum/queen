package com.queen.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.queen.system.entity.Tenant;
import com.queen.core.mp.base.BaseService;


/**
 * 服务类
 *
 * @author jensen
 */
public interface ITenantService extends BaseService<Tenant> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param tenant
	 * @return
	 */
	IPage<Tenant> selectTenantPage(IPage<Tenant> page, Tenant tenant);

	/**
	 * 新增
	 *
	 * @param tenant
	 * @return
	 */
	boolean saveTenant(Tenant tenant);

}
