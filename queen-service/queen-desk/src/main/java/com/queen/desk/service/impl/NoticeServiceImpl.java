package com.queen.desk.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.queen.desk.entity.Notice;
import com.queen.desk.service.INoticeService;
import com.queen.core.mp.base.BaseServiceImpl;

import com.queen.desk.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author jensen
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<NoticeMapper, Notice> implements INoticeService {

	@Override
	public IPage<Notice> selectNoticePage(IPage<Notice> page, Notice notice) {
		return page.setRecords(baseMapper.selectNoticePage(page, notice));
	}

}
