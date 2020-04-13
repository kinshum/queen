package com.example.demo.controller;

import com.example.demo.entity.Notice;
import com.example.demo.service.IDynamicService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.queen.core.tool.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 多数据源
 *
 * @author jensen
 */
@RestController
@AllArgsConstructor
@RequestMapping("dynamic")
@Api(value = "多数据源接口", tags = "多数据源")
public class DynamicController {

	private IDynamicService dynamicService;

	/**
	 * master列表
	 */
	@GetMapping("/master-list")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "master列表", notes = "master列表")
	public R<List<Notice>> masterList() {
		List<Notice> list = dynamicService.masterList();
		return R.data(list);
	}

	/**
	 * slave列表
	 */
	@GetMapping("/slave-list")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "slave列表", notes = "slave列表")
	public R<List<Notice>> slaveList() {
		List<Notice> list = dynamicService.slaveList();
		return R.data(list);
	}

}
