package com.queen.schedule;

import com.queen.core.launch.QueenApplication;
import com.queen.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jensen
 * @description schedule启动类
 * @date 2020/1/9 14:20
 */
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class ScheduleApplication {

    public static void main(String[] args) {
        QueenApplication.run(AppConstant.APPLICATION_SCHEDULE_NAME, ScheduleApplication.class, args);
    }

}
