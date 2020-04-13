package com.queen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.queen.system.entity.Param;
import com.queen.system.vo.ParamVO;



import java.util.List;

/**
 * Mapper 接口
 *
 * @author jensen
 */
public interface ParamMapper extends BaseMapper<Param> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param param
	 * @return
	 */
	List<ParamVO> selectParamPage(IPage page, ParamVO param);

}
