package com.queen.core.tool.support.xss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Xss配置类
 *
 * @author jensen
 */
@Data
@ConfigurationProperties("queen.xss.url")
public class XssUrlProperties {

	private final List<String> excludePatterns = new ArrayList<>();

}
