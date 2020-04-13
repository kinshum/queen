package com.queen.system.wrapper;


import com.queen.common.constant.CommonConstant;
import com.queen.core.tool.node.ForestNodeMerger;
import com.queen.core.tool.node.INode;
import com.queen.core.tool.utils.BeanUtil;
import com.queen.core.tool.utils.Func;
import com.queen.core.tool.utils.SpringUtil;
import com.queen.core.mp.support.BaseEntityWrapper;

import com.queen.system.entity.Dict;
import com.queen.system.service.IDictService;
import com.queen.system.vo.DictVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author jensen
 */
public class DictWrapper extends BaseEntityWrapper<Dict, DictVO> {

	private static IDictService dictService;

	static {
		dictService = SpringUtil.getBean(IDictService.class);
	}

	public static DictWrapper build() {
		return new DictWrapper();
	}

	@Override
	public DictVO entityVO(Dict dict) {
		DictVO dictVO = BeanUtil.copy(dict, DictVO.class);
		if (Func.equals(dict.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			dictVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Dict parent = dictService.getById(dict.getParentId());
			dictVO.setParentName(parent.getDictValue());
		}
		return dictVO;
	}

	public List<INode> listNodeVO(List<Dict> list) {
		List<INode> collect = list.stream().map(dict -> BeanUtil.copy(dict, DictVO.class)).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
