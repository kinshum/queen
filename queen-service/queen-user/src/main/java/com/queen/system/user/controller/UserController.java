package com.queen.system.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.queen.core.secure.QueenUser;
import com.queen.core.tool.api.R;
import com.queen.core.tool.constant.QueenConstant;
import com.queen.core.tool.utils.Func;
import com.queen.system.user.entity.User;
import com.queen.system.user.service.IUserService;
import com.queen.system.user.vo.UserVO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import com.queen.core.mp.support.Condition;
import com.queen.core.mp.support.Query;


import com.queen.system.user.wrapper.UserWrapper;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author jensen
 */
@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {

	private IUserService userService;

	/**
	 * 查询单条
	 */
	@ApiOperation(value = "查看详情", notes = "传入id")
	@GetMapping("/detail")
	public R<UserVO> detail(User user) {
		User detail = userService.getOne(Condition.getQueryWrapper(user));
		return R.data(UserWrapper.build().entityVO(detail));
	}

	/**
	 * 查询单条
	 */
	@ApiOperation(value = "查看详情", notes = "传入id")
	@GetMapping("/info")
	public R<UserVO> info(QueenUser user) {
		User detail = userService.getById(user.getUserId());
		return R.data(UserWrapper.build().entityVO(detail));
	}

	/**
	 * 用户列表
	 */
	@GetMapping("/list")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "account", value = "账号名", paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "realName", value = "姓名", paramType = "query", dataType = "string")
	})
	@ApiOperation(value = "列表", notes = "传入account和realName")
	public R<IPage<UserVO>> list(@ApiIgnore @RequestParam Map<String, Object> user, Query query, QueenUser queenUser) {
		QueryWrapper<User> queryWrapper = Condition.getQueryWrapper(user, User.class);
		IPage<User> pages = userService.page(Condition.getPage(query), (!queenUser.getTenantId().equals(QueenConstant.ADMIN_TENANT_ID)) ? queryWrapper.lambda().eq(User::getTenantId, queenUser.getTenantId()) : queryWrapper);
		return R.data(UserWrapper.build().pageVO(pages));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增或修改", notes = "传入User")
	public R submit(@Valid @RequestBody User user) {
		return R.status(userService.submit(user));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入User")
	public R update(@Valid @RequestBody User user) {
		return R.status(userService.updateById(user));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperation(value = "删除", notes = "传入地基和")
	public R remove(@RequestParam String ids) {
		return R.status(userService.deleteLogic(Func.toLongList(ids)));
	}


	/**
	 * 设置菜单权限
	 *
	 * @param userIds
	 * @param roleIds
	 * @return
	 */
	@PostMapping("/grant")
	@ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
	public R grant(@ApiParam(value = "userId集合", required = true) @RequestParam String userIds,
				   @ApiParam(value = "roleId集合", required = true) @RequestParam String roleIds) {
		boolean temp = userService.grant(userIds, roleIds);
		return R.status(temp);
	}

	@PostMapping("/reset-password")
	@ApiOperation(value = "初始化密码", notes = "传入userId集合")
	public R resetPassword(@ApiParam(value = "userId集合", required = true) @RequestParam String userIds) {
		boolean temp = userService.resetPassword(userIds);
		return R.status(temp);
	}

	/**
	 * 修改密码
	 *
	 * @param oldPassword
	 * @param newPassword
	 * @param newPassword1
	 * @return
	 */
	@PostMapping("/update-password")
	@ApiOperation(value = "修改密码", notes = "传入密码")
	public R updatePassword(QueenUser user, @ApiParam(value = "旧密码", required = true) @RequestParam String oldPassword,
							@ApiParam(value = "新密码", required = true) @RequestParam String newPassword,
							@ApiParam(value = "新密码", required = true) @RequestParam String newPassword1) {
		boolean temp = userService.updatePassword(user.getUserId(), oldPassword, newPassword, newPassword1);
		return R.status(temp);
	}

	/**
	 * 用户列表
	 *
	 * @param user
	 * @return
	 */
	@GetMapping("/user-list")
	@ApiOperation(value = "用户列表", notes = "传入user")
	public R<List<User>> userList(User user) {
		List<User> list = userService.list(Condition.getQueryWrapper(user));
		return R.data(list);
	}

}
