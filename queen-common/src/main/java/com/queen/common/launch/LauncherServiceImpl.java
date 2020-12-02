package com.queen.common.launch;

import com.queen.common.constant.LauncherConstant;
import com.queen.core.launch.service.LauncherService;
import com.queen.core.launch.utils.PropsUtil;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;

/**
 * 启动参数拓展
 *
 * @author jensen
 */
public class LauncherServiceImpl implements LauncherService {


    @Override
    public void launcher(SpringApplicationBuilder builder, String appName, String profile, boolean isLocalDev) {
        Properties props = System.getProperties();
        PropsUtil.setProperty(props, "spring.cloud.nacos.discovery.server-addr", LauncherConstant.nacosAddr(profile));
        PropsUtil.setProperty(props, "spring.cloud.nacos.config.server-addr", LauncherConstant.nacosAddr(profile));
        PropsUtil.setProperty(props, "spring.cloud.sentinel.transport.dashboard", LauncherConstant.sentinelAddr(profile));
        PropsUtil.setProperty(props, "spring.zipkin.base-url", LauncherConstant.zipkinAddr(profile));
        // 开启elk日志
        //PropsUtil.setProperty(props,"queen.log.elk.destination", LauncherConstant.elkAddr(profile));
    }
}
