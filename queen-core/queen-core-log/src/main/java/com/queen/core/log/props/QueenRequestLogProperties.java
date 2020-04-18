package com.queen.core.log.props;

import com.queen.core.launch.log.QueenLogLevel;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 日志配置
 *
 * @author jensen
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties(QueenLogLevel.REQ_LOG_PROPS_PREFIX)
public class QueenRequestLogProperties {

	/**
	 * 日志级别配置，默认：BODY
	 */
	private QueenLogLevel level = QueenLogLevel.BODY;
}
