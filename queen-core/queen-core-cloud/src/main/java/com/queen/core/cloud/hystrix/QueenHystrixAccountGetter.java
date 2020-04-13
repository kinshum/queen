package com.queen.core.cloud.hystrix;


import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息获取器，用于请求头传递
 *
 * @author jensen
 */
public interface QueenHystrixAccountGetter {

	/**
	 * 账号信息获取器
	 *
	 * @param request HttpServletRequest
	 * @return account 信息
	 */
	@Nullable
	String get(HttpServletRequest request);
}
