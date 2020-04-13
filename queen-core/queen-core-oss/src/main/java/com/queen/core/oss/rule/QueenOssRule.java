package com.queen.core.oss.rule;

import com.queen.core.tool.utils.DateUtil;
import com.queen.core.tool.utils.FileUtil;
import com.queen.core.tool.utils.StringPool;
import com.queen.core.tool.utils.StringUtil;
import lombok.AllArgsConstructor;


/**
 * 默认存储桶生成规则
 *
 * @author jensen
 */
@AllArgsConstructor
public class QueenOssRule implements OssRule {

	@Override
	public String bucketName(String bucketName) {
		return bucketName;
	}

	@Override
	public String fileName(String originalFilename) {
		return "upload" + StringPool.SLASH + DateUtil.today() + StringPool.SLASH + StringUtil.randomUUID() + StringPool.DOT + FileUtil.getFileExtension(originalFilename);
	}

}
