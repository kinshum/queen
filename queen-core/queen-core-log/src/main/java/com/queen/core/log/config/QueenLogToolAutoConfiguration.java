package com.queen.core.log.config;

import com.queen.core.launch.props.QueenProperties;
import com.queen.core.launch.server.ServerInfo;
import com.queen.core.log.aspect.ApiLogAspect;
import com.queen.core.log.event.ApiLogListener;
import lombok.AllArgsConstructor;
;
import com.queen.core.log.event.ErrorLogListener;
import com.queen.core.log.event.UsualLogListener;
import com.queen.core.log.feign.ILogClient;
import com.queen.core.log.logger.QueenLogger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志工具自动配置
 *
 * @author jensen
 */
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class QueenLogToolAutoConfiguration {

	private final ILogClient logService;
	private final ServerInfo serverInfo;
	private final QueenProperties queenProperties;

	@Bean
	public ApiLogAspect apiLogAspect() {
		return new ApiLogAspect();
	}

	@Bean
	public QueenLogger queenLogger() {
		return new QueenLogger();
	}

	@Bean
	public ApiLogListener apiLogListener() {
		return new ApiLogListener(logService, serverInfo, queenProperties);
	}

	@Bean
	public ErrorLogListener errorEventListener() {
		return new ErrorLogListener(logService, serverInfo, queenProperties);
	}

	@Bean
	public UsualLogListener queenEventListener() {
		return new UsualLogListener(logService, serverInfo, queenProperties);
	}

}
