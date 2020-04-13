package com.example.demo.feign;

import com.example.demo.entity.Notice;

import com.queen.core.launch.constant.AppConstant;
import com.queen.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Notice Feign接口类
 *
 * @author jensen
 */
@FeignClient(
	value = AppConstant.APPLICATION_DESK_NAME
)
public interface INoticeClient {

	String API_PREFIX = "/client";
	String TOP = API_PREFIX + "/top";

	/**
	 * 获取notice列表
	 *
	 * @param number
	 * @return
	 */
	@GetMapping(TOP)
	R<List<Notice>> top(@RequestParam("number") Integer number);

}
