package com.queen.auth;

import com.queen.core.launch.QueenApplication;
import com.queen.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户认证服务器
 *
 * @author jensen
 */
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class AuthApplication {

	public static void main(String[] args) {
		QueenApplication.run(AppConstant.APPLICATION_AUTH_NAME, AuthApplication.class, args);
	}

}
