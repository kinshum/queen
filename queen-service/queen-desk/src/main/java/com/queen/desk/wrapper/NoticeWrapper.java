package com.queen.desk.wrapper;

import com.queen.core.tool.api.R;
import com.queen.core.tool.utils.BeanUtil;
import com.queen.core.tool.utils.SpringUtil;
import com.queen.desk.entity.Notice;
import com.queen.desk.vo.NoticeVO;
import com.queen.core.mp.support.BaseEntityWrapper;


import com.queen.system.feign.IDictClient;

/**
 * Notice包装类,返回视图层所需的字段
 *
 * @author jensen
 */
public class NoticeWrapper extends BaseEntityWrapper<Notice, NoticeVO> {

	private static IDictClient dictClient;

	static {
		dictClient = SpringUtil.getBean(IDictClient.class);
	}

	public static NoticeWrapper build() {
		return new NoticeWrapper();
	}

	@Override
	public NoticeVO entityVO(Notice notice) {
		NoticeVO noticeVO = BeanUtil.copy(notice, NoticeVO.class);
		R<String> dict = dictClient.getValue("notice", noticeVO.getCategory());
		if (dict.isSuccess()) {
			String categoryName = dict.getData();
			noticeVO.setCategoryName(categoryName);
		}
		return noticeVO;
	}

}
