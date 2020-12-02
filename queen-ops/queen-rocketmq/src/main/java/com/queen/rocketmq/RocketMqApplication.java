package com.queen.rocketmq;

import com.queen.core.launch.QueenApplication;
import com.queen.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author jensen
 * @description rocketMq 启动类
 * @date 2020/1/7 17:21
 */
@SpringCloudApplication
public class RocketMqApplication {

    public static void main(String[] args) {
        QueenApplication.run("queen-rocketmq", RocketMqApplication.class, args);
    }

}
