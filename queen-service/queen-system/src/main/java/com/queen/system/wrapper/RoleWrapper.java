package com.queen.system.wrapper;

import com.queen.common.constant.CommonConstant;
import com.queen.core.tool.node.ForestNodeMerger;
import com.queen.core.tool.node.INode;
import com.queen.core.tool.utils.BeanUtil;
import com.queen.core.tool.utils.Func;
import com.queen.core.tool.utils.SpringUtil;
import com.queen.system.entity.Role;
import com.queen.system.vo.RoleVO;
import com.queen.system.service.IRoleService;

import com.queen.core.mp.support.BaseEntityWrapper;



import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author jensen
 */
public class RoleWrapper extends BaseEntityWrapper<Role, RoleVO> {

	private static IRoleService roleService;

	static {
		roleService = SpringUtil.getBean(IRoleService.class);
	}

	public static RoleWrapper build() {
		return new RoleWrapper();
	}

	@Override
	public RoleVO entityVO(Role role) {
		RoleVO roleVO = BeanUtil.copy(role, RoleVO.class);
		if (Func.equals(role.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			roleVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Role parent = roleService.getById(role.getParentId());
			roleVO.setParentName(parent.getRoleName());
		}
		return roleVO;
	}

	public List<INode> listNodeVO(List<Role> list) {
		List<INode> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
