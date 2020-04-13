package com.queen.seata.storage;

import com.queen.core.launch.QueenApplication;
import com.queen.core.launch.constant.AppConstant;
import com.queen.core.transaction.annotation.SeataCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Storage启动器
 *
 * @author jensen
 */
@SeataCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class SeataStorageApplication {

	public static void main(String[] args) {
		QueenApplication.run("queen-seata-storage", SeataStorageApplication.class, args);
	}

}

