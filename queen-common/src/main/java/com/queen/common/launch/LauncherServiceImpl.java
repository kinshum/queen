package com.queen.common.launch;

import com.queen.common.constant.LauncherConstant;
import com.queen.core.launch.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

/**
 * 启动参数拓展
 *
 * @author jensen
 */
public class LauncherServiceImpl implements LauncherService {


	@Override
	public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
		Properties props = System.getProperties();
		props.setProperty("spring.cloud.nacos.discovery.server-addr", LauncherConstant.nacosAddr(profile));
		props.setProperty("spring.cloud.nacos.config.server-addr", LauncherConstant.nacosAddr(profile));
		props.setProperty("spring.cloud.sentinel.transport.dashboard", LauncherConstant.sentinelAddr(profile));
		props.setProperty("spring.zipkin.base-url", LauncherConstant.zipkinAddr(profile));
	}


}
