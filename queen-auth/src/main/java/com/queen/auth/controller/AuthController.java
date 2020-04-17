package com.queen.auth.controller;

import com.queen.auth.granter.ITokenGranter;
import com.queen.auth.granter.TokenGranterBuilder;
import com.queen.auth.granter.TokenParameter;
import com.queen.auth.utils.TokenUtil;
import com.queen.common.cache.CacheNames;
import com.queen.core.log.annotation.ApiLog;
import com.queen.core.secure.AuthInfo;
import com.queen.core.tool.api.R;
import com.queen.core.tool.support.Kv;
import com.queen.core.tool.utils.Func;
import com.queen.core.tool.utils.RedisUtil;
import com.queen.core.tool.utils.WebUtil;
import com.queen.system.user.entity.UserInfo;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 认证模块
 *
 * @author jensen
 */
@RestController
@AllArgsConstructor
@Api(value = "用户授权认证", tags = "授权接口")
@Slf4j
public class AuthController {

	private RedisUtil redisUtil;

	@PostMapping("token")
	@ApiOperation(value = "获取认证token", notes = "传入租户ID:tenantId,账号:account,密码:password")
	public R<AuthInfo> token(@ApiParam(value = "授权类型", required = true) @RequestParam(defaultValue = "password", required = false) String grantType,
							 @ApiParam(value = "刷新令牌") @RequestParam(required = false) String refreshToken,
							 @ApiParam(value = "租户ID", required = true) @RequestParam(defaultValue = "000000", required = false) String tenantId,
							 @ApiParam(value = "账号") @RequestParam(required = false) String account,
							 @ApiParam(value = "密码") @RequestParam(required = false) String password) {

		String userType = Func.toStr(WebUtil.getRequest().getHeader(TokenUtil.USER_TYPE_HEADER_KEY), TokenUtil.DEFAULT_USER_TYPE);

		TokenParameter tokenParameter = new TokenParameter();
		tokenParameter.getArgs().set("tenantId", tenantId)
			.set("account", account)
			.set("password", password)
			.set("grantType", grantType)
			.set("refreshToken", refreshToken)
			.set("userType", userType);

		ITokenGranter granter = TokenGranterBuilder.getGranter(grantType);
		UserInfo userInfo = granter.grant(tokenParameter);

		if (userInfo == null || userInfo.getUser() == null || userInfo.getUser().getId() == null) {
			return R.fail(TokenUtil.USER_NOT_FOUND);
		}

		return R.data(TokenUtil.createAuthInfo(userInfo));
	}

	@ApiLog("获取验证码")
	@GetMapping("/captcha")
	public R<Kv> captcha() {
		SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
		String verCode = specCaptcha.text().toLowerCase();
		String key = UUID.randomUUID().toString();
		// 存入redis并设置过期时间为30分钟
		redisUtil.set("queen_test","queen_test_hello!!!");
		log.info("redis_key 测试--{}",redisUtil.get("queen_test"));
		redisUtil.set(CacheNames.CAPTCHA_KEY + key, verCode, 30L, TimeUnit.MINUTES);
		// 将key和base64返回给前端
		return R.data(Kv.init().set("key", key).set("image", specCaptcha.toBase64()));
	}

}
