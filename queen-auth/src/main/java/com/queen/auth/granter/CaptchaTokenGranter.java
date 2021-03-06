package com.queen.auth.granter;

import com.queen.auth.enums.QueenUserEnum;
import com.queen.auth.utils.TokenUtil;
import com.queen.common.cache.CacheNames;
import com.queen.core.log.exception.ServiceException;
import com.queen.core.redis.cache.QueenRedis;
import com.queen.core.tool.api.R;
import com.queen.core.tool.utils.*;
import com.queen.system.user.entity.UserInfo;
import com.queen.system.user.feign.IUserClient;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码TokenGranter
 *
 * @author jensen
 */
@Component
@AllArgsConstructor
public class CaptchaTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "captcha";

	private IUserClient userClient;
	private QueenRedis queenRedis;

	@Override
	public UserInfo grant(TokenParameter tokenParameter) {
		HttpServletRequest request = WebUtil.getRequest();

		String key = request.getHeader(TokenUtil.CAPTCHA_HEADER_KEY);
		String code = request.getHeader(TokenUtil.CAPTCHA_HEADER_CODE);
		// 获取验证码
		String redisCode = String.valueOf(queenRedis.get(CacheNames.CAPTCHA_KEY + key));
		// 判断验证码
		if (code == null || !StringUtil.equalsIgnoreCase(redisCode, code)) {
			throw new ServiceException(TokenUtil.CAPTCHA_NOT_CORRECT);
		}

		String tenantId = tokenParameter.getArgs().getStr("tenantId");
		String account = tokenParameter.getArgs().getStr("account");
		String password = tokenParameter.getArgs().getStr("password");
		UserInfo userInfo = null;
		if (Func.isNoneBlank(account, password)) {
			// 获取用户类型
			String userType = tokenParameter.getArgs().getStr("userType");
			R<UserInfo> result;
			// 根据不同用户类型调用对应的接口返回数据，用户可自行拓展
			if (userType.equals(QueenUserEnum.WEB.getName())) {
				result = userClient.userInfo(tenantId, account, DigestUtil.encrypt(password));
			} else if (userType.equals(QueenUserEnum.APP.getName())) {
				result = userClient.userInfo(tenantId, account, DigestUtil.encrypt(password));
			} else {
				result = userClient.userInfo(tenantId, account, DigestUtil.encrypt(password));
			}
			userInfo = result.isSuccess() ? result.getData() : null;
		}
		return userInfo;
	}

}
