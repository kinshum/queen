package com.queen.core.boot.tenant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 多租户配置
 *
 * @author jensen
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "queen.tenant")
public class QueenTenantProperties {

	/**
	 * 多租户字段名称
	 */
	private String column = "tenant_id";

	/**
	 * 多租户数据表
	 */
	private List<String> tables = new ArrayList<>();

	/**
	 * 多租户系统数据表
	 */
	private List<String> queenTables = Arrays.asList("queen_notice", "queen_log_api", "queen_log_error", "queen_log_usual");
}
