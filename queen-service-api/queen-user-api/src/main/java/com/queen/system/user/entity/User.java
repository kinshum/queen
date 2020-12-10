package com.queen.system.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.queen.core.mp.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 实体类
 *
 * @author jensen
 */
@Data
@TableName("queen_user")
@EqualsAndHashCode(callSuper = true)
public class User extends TenantEntity {

	private static final long serialVersionUID = 1L;


	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 真名
	 */
	private String realName;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机
	 */
	private String phone;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 角色id
	 */
	private String roleId;
	/**
	 * 部门id
	 */
	private String deptId;


}
