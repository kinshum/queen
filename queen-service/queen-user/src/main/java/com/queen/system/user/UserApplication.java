package com.queen.system.user;

import com.queen.core.launch.QueenApplication;

import com.queen.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户启动器
 *
 * @author jensen
 */
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class UserApplication {

	public static void main(String[] args) {
		QueenApplication.run(AppConstant.APPLICATION_USER_NAME, UserApplication.class, args);
	}

}
