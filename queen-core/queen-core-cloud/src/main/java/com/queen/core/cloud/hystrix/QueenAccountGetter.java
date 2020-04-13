package com.queen.core.cloud.hystrix;


import com.queen.core.secure.QueenUser;
import com.queen.core.secure.utils.SecureUtil;
import com.queen.core.tool.utils.Charsets;
import com.queen.core.tool.utils.UrlUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息获取器
 *
 * @author jensen
 */
public class QueenAccountGetter implements QueenHystrixAccountGetter {

	@Override
	public String get(HttpServletRequest request) {
		QueenUser account = SecureUtil.getUser();
		if (account == null) {
			return null;
		}
		// 增加用户头, 123[admin]
		String xAccount = String.format("%s[%s]", account.getUserId(), account.getUserName());
		return UrlUtil.encodeURL(xAccount, Charsets.UTF_8);
	}

}
