package com.queen.system.feign;

import com.queen.system.entity.Dept;
import com.queen.system.entity.Role;
import com.queen.system.service.IRoleService;
import lombok.AllArgsConstructor;

import com.queen.system.service.IDeptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 系统服务Feign实现类
 *
 * @author jensen
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class SysClient implements ISysClient {

	IDeptService deptService;

	IRoleService roleService;

	@Override
	@GetMapping(API_PREFIX + "/getDeptName")
	public String getDeptName(Integer id) {
		return deptService.getById(id).getDeptName();
	}

	@Override
	@GetMapping(API_PREFIX + "/getDept")
	public Dept getDept(Integer id) {
		return deptService.getById(id);
	}

	@Override
	@GetMapping(API_PREFIX + "/getRoleName")
	public String getRoleName(Integer id) {
		return roleService.getById(id).getRoleName();
	}

	@Override
	@GetMapping(API_PREFIX + "/getRoleAlias")
	public String getRoleAlias(Integer id) {
		return roleService.getById(id).getRoleAlias();
	}

	@Override
	@GetMapping(API_PREFIX + "/getRole")
	public Role getRole(Integer id) {
		return roleService.getById(id);
	}
}
