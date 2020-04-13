package com.queen.core.log;


import com.queen.core.launch.QueenApplication;
import com.queen.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 日志服务
 *
 * @author jensen
 */
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class LogApplication {

	public static void main(String[] args) {
		QueenApplication.run(AppConstant.APPLICATION_LOG_NAME, LogApplication.class, args);
	}

}
