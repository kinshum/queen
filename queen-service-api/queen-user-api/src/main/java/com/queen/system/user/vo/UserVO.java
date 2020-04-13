package com.queen.system.user.vo;

import com.queen.system.user.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视图实体类
 *
 * @author jensen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserVO对象", description = "UserVO对象")
public class UserVO extends User {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 角色名
	 */
	private String roleName;

	/**
	 * 部门名
	 */
	private String deptName;

	/**
	 * 性别
	 */
	private String sexName;
}
