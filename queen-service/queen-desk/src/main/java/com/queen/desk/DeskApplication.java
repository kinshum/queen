package com.queen.desk;

import com.queen.core.launch.QueenApplication;
import com.queen.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Desk启动器
 *
 * @author jensen
 */
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class DeskApplication {

	public static void main(String[] args) {
		QueenApplication.run(AppConstant.APPLICATION_DESK_NAME, DeskApplication.class, args);
	}

}

