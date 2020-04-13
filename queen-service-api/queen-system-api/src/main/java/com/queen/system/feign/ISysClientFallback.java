package com.queen.system.feign;

import com.queen.system.entity.Dept;
import com.queen.system.entity.Role;
import org.springframework.stereotype.Component;

/**
 * Feign失败配置
 *
 * @author jensen
 */
@Component
public class ISysClientFallback implements ISysClient {
	@Override
	public String getDeptName(Integer id) {
		return null;
	}

	@Override
	public Dept getDept(Integer id) {
		return null;
	}

	@Override
	public String getRoleName(Integer id) {
		return null;
	}

	@Override
	public String getRoleAlias(Integer id) {
		return null;
	}

	@Override
	public Role getRole(Integer id) {
		return null;
	}
}
