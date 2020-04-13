package com.queen.core.secure.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * secure放行额外配置
 *
 * @author jensen
 */
@Data
@ConfigurationProperties("queen.secure.url")
public class QueenSecureProperties {

	private final List<String> excludePatterns = new ArrayList<>();

}
