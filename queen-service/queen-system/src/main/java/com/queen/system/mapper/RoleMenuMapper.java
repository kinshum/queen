package com.queen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.queen.system.entity.RoleMenu;
import com.queen.system.vo.RoleMenuVO;


import java.util.List;

/**
 * Mapper 接口
 *
 * @author jensen
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

	/**
	 * 自定义分页
	 * @param page
	 * @param roleMenu
	 * @return
	 */
	List<RoleMenuVO> selectRoleMenuPage(IPage page, RoleMenuVO roleMenu);

}
