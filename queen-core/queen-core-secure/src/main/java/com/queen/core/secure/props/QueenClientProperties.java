package com.queen.core.secure.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户端校验配置
 *
 * @author jensen
 */
@Data
@ConfigurationProperties("queen.secure")
public class QueenClientProperties {

	private final List<ClientSecure> client = new ArrayList<>();

}
