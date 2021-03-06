
package com.queen.desk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.queen.desk.entity.Notice;
import com.queen.core.mp.base.BaseService;


/**
 * 服务类
 *
 * @author jensen
 */
public interface INoticeService extends BaseService<Notice> {

	/**
	 * 自定义分页
	 * @param page
	 * @param notice
	 * @return
	 */
	IPage<Notice> selectNoticePage(IPage<Notice> page, Notice notice);

}
