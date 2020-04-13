package com.queen.auth.granter;

import com.queen.system.user.entity.UserInfo;

/**
 * 授权认证统一接口.
 *
 * @author jensen
 */
public interface ITokenGranter {

	/**
	 * 获取用户信息
	 *
	 * @param tokenParameter 授权参数
	 * @return UserInfo
	 */
	UserInfo grant(TokenParameter tokenParameter);

}
