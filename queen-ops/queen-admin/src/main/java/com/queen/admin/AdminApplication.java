package com.queen.admin;

import com.queen.core.launch.QueenApplication;
import com.queen.core.launch.constant.AppConstant;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

import org.springframework.cloud.client.SpringCloudApplication;

/**
 * admin启动器
 *
 * @author jensen
 */
@EnableAdminServer
@SpringCloudApplication
public class AdminApplication {

	public static void main(String[] args) {
		QueenApplication.run(AppConstant.APPLICATION_ADMIN_NAME, AdminApplication.class, args);
	}

}
