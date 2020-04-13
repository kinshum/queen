
package com.queen.system.wrapper;

import com.queen.common.constant.CommonConstant;
import com.queen.core.tool.node.ForestNodeMerger;
import com.queen.core.tool.node.INode;
import com.queen.core.tool.utils.BeanUtil;
import com.queen.core.tool.utils.Func;
import com.queen.core.tool.utils.SpringUtil;
import com.queen.system.entity.Dept;
import com.queen.system.vo.DeptVO;
import com.queen.core.mp.support.BaseEntityWrapper;


import com.queen.system.service.IDeptService;


import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author jensen
 */
public class DeptWrapper extends BaseEntityWrapper<Dept, DeptVO> {

	private static IDeptService deptService;

	static {
		deptService = SpringUtil.getBean(IDeptService.class);
	}

	public static DeptWrapper build() {
		return new DeptWrapper();
	}

	@Override
	public DeptVO entityVO(Dept dept) {
		DeptVO deptVO = BeanUtil.copy(dept, DeptVO.class);
		if (Func.equals(dept.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			deptVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Dept parent = deptService.getById(dept.getParentId());
			deptVO.setParentName(parent.getDeptName());
		}
		return deptVO;
	}

	public List<INode> listNodeVO(List<Dept> list) {
		List<INode> collect = list.stream().map(dept -> BeanUtil.copy(dept, DeptVO.class)).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
