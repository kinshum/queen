package com.queen.core.boot.tenant;


import com.queen.core.tool.utils.RandomType;
import com.queen.core.tool.utils.StringUtil;

/**
 * 租户id生成器
 *
 * @author jensen
 */
public class QueenTenantId implements TenantId {
	@Override
	public String generate() {
		return StringUtil.random(6, RandomType.INT);
	}
}
