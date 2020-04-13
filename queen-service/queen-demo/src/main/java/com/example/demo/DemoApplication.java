package com.example.demo;


import com.queen.core.launch.QueenApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Demo启动器
 *
 * @author jensen
 */
@SpringCloudApplication
public class DemoApplication {

	public static void main(String[] args) {
		QueenApplication.run("queen-demo", DemoApplication.class, args);
	}

}

