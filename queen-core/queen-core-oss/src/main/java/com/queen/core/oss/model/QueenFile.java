package com.queen.core.oss.model;

import lombok.Data;

/**
 * queenFile
 *
 * @author jensen
 */
@Data
public class QueenFile {
	/**
	 * 文件地址
	 */
	private String link;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 原始文件名
	 */
	private String originalName;
}
