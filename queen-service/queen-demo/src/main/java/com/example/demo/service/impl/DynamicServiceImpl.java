package com.example.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo.entity.Notice;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.service.IDynamicService;

import com.queen.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DynamicServiceImpl
 *
 * @author jensen
 */
@Service
public class DynamicServiceImpl extends BaseServiceImpl<NoticeMapper, Notice> implements IDynamicService {

	@Override
	public List<Notice> masterList() {
		return this.list();
	}

	@Override
	@DS("slave")
	public List<Notice> slaveList() {
		return this.list();
	}
}
