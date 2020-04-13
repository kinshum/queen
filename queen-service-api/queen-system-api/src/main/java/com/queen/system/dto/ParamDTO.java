package com.queen.system.dto;

import com.queen.system.entity.Param;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据传输对象实体类
 *
 * @author jensen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamDTO extends Param {
	private static final long serialVersionUID = 1L;

}
