package com.queen.core.boot.config;

import com.queen.core.launch.props.QueenProperties;
import com.queen.core.tool.constant.SystemConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置类
 *
 * @author jensen
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
	QueenProperties.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@AllArgsConstructor
public class QueenBootAutoConfiguration {

	private QueenProperties queenProperties;

	/**
	 * 全局变量定义
	 *
	 * @return SystemConstant
	 */
	@Bean
	public SystemConstant fileConst() {
		SystemConstant me = SystemConstant.me();

		//设定开发模式
		me.setDevMode(("dev".equals(queenProperties.getEnv())));

		//设定文件上传远程地址
		me.setDomain(queenProperties.get("upload-domain", "http://localhost:8888"));

		//设定文件上传是否为远程模式
		me.setRemoteMode(queenProperties.getBoolean("remote-mode", true));

		//远程上传地址
		me.setRemotePath(queenProperties.get("remote-path", System.getProperty("user.dir") + "/work/queen"));

		//设定文件上传头文件夹
		me.setUploadPath(queenProperties.get("upload-path", "/upload"));

		//设定文件下载头文件夹
		me.setDownloadPath(queenProperties.get("download-path", "/download"));

		//设定上传图片是否压缩
		me.setCompress(queenProperties.getBoolean("compress", false));

		//设定上传图片压缩比例
		me.setCompressScale(queenProperties.getDouble("compress-scale", 2.00));

		//设定上传图片缩放选择:true放大;false缩小
		me.setCompressFlag(queenProperties.getBoolean("compress-flag", false));

		return me;
	}

}
