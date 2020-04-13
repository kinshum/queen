package com.example.demo.service;

import com.example.demo.entity.Notice;
import com.queen.core.mp.base.BaseService;


import java.util.List;

/**
 * 服务类
 *
 * @author jensen
 */
public interface IDynamicService extends BaseService<Notice> {

	/**
	 * master数据源的列表
	 *
	 * @return
	 */
	List<Notice> masterList();

	/**
	 * slave数据源的列表
	 *
	 * @return
	 */
	List<Notice> slaveList();

}
