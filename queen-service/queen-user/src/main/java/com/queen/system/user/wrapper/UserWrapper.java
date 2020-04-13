package com.queen.system.user.wrapper;

import com.queen.core.tool.api.R;
import com.queen.core.tool.utils.BeanUtil;
import com.queen.core.tool.utils.Func;
import com.queen.core.tool.utils.SpringUtil;
import com.queen.system.feign.IDictClient;
import com.queen.system.user.entity.User;
import com.queen.system.user.service.IUserService;
import com.queen.system.user.vo.UserVO;
import com.queen.core.mp.support.BaseEntityWrapper;



import java.util.List;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author jensen
 */
public class UserWrapper extends BaseEntityWrapper<User, UserVO> {

	private static IUserService userService;

	private static IDictClient dictClient;

	static {
		userService = SpringUtil.getBean(IUserService.class);
		dictClient = SpringUtil.getBean(IDictClient.class);
	}

	public static UserWrapper build() {
		return new UserWrapper();
	}

	@Override
	public UserVO entityVO(User user) {
		UserVO userVO = BeanUtil.copy(user, UserVO.class);
		List<String> roleName = userService.getRoleName(user.getRoleId());
		List<String> deptName = userService.getDeptName(user.getDeptId());
		userVO.setRoleName(Func.join(roleName));
		userVO.setDeptName(Func.join(deptName));
		R<String> dict = dictClient.getValue("sex", Func.toInt(user.getSex()));
		if (dict.isSuccess()) {
			userVO.setSexName(dict.getData());
		}
		return userVO;
	}

}
