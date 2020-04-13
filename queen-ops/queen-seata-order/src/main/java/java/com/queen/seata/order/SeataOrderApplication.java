package java.com.queen.seata.order;

import com.queen.core.launch.QueenApplication;
import com.queen.core.launch.constant.AppConstant;
import com.queen.core.transaction.annotation.SeataCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Order启动器
 *
 * @author jensen
 */
@SeataCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class SeataOrderApplication {

	public static void main(String[] args) {
		QueenApplication.run("queen-seata-order", SeataOrderApplication.class, args);
	}

}

