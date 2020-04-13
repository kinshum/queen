package com.queen.zero;

import com.queen.core.launch.QueenApplication;
import com.queen.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * ZeroApplication
 *
 * @author jensen
 */
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class ZeroApplication {

	public static void main(String[] args) {
		QueenApplication.run("queen-zero", ZeroApplication.class, args);
	}

}

