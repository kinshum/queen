package com.queen.gateway.props;

import com.queen.core.launch.constant.AppConstant;
import lombok.Data;


/**
 * Swagger聚合文档属性
 *
 * @author jensen
 */
@Data
public class RouteResource {

	/**
	 * 文档名
	 */
	private String name;

	/**
	 * 文档所在服务地址
	 */
	private String location;

	/**
	 * 文档版本
	 */
	private String version = AppConstant.APPLICATION_VERSION;

}
