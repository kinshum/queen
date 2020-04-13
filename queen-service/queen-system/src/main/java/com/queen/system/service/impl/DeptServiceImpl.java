
package com.queen.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.queen.core.tool.node.ForestNodeMerger;
import com.queen.system.entity.Dept;
import com.queen.system.vo.DeptVO;


import com.queen.system.mapper.DeptMapper;
import com.queen.system.service.IDeptService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author jensen
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

	@Override
	public IPage<DeptVO> selectDeptPage(IPage<DeptVO> page, DeptVO dept) {
		return page.setRecords(baseMapper.selectDeptPage(page, dept));
	}

	@Override
	public List<DeptVO> tree(String tenantId) {
		return ForestNodeMerger.merge(baseMapper.tree(tenantId));
	}

}
