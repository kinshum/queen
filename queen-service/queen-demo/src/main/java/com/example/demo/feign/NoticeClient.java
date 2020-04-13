package com.example.demo.feign;

import com.example.demo.entity.Notice;
import com.example.demo.mapper.NoticeMapper;
import com.queen.core.tool.api.R;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Notice Feign
 *
 * @author jensen
 */
@ApiIgnore()
@RestController
@AllArgsConstructor
public class NoticeClient implements INoticeClient {

	private NoticeMapper mapper;

	@Override
	@GetMapping(TOP)
	public R<List<Notice>> top(Integer number) {
		return R.data(mapper.topList(number));
	}

}
