package com.queen.core.secure;

import lombok.Data;

/**
 * TokenInfo
 *
 * @author jensen
 */
@Data
public class TokenInfo {

	/**
	 * 令牌值
	 */
	private String token;

	/**
	 * 过期秒数
	 */
	private int expire;

}
