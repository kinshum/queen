package com.queen.system.feign;

import com.queen.core.tool.api.R;
import com.queen.system.entity.Dict;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Feign失败配置
 *
 * @author jensen
 */
@Component
public class IDictClientFallback implements IDictClient {
	@Override
	public R<String> getValue(String code, Integer dictKey) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<Dict>> getList(String code) {
		return R.fail("获取数据失败");
	}
}
