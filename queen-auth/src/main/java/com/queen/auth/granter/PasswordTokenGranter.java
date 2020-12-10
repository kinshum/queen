package com.queen.auth.granter;

import com.queen.core.tool.api.R;
import com.queen.core.tool.utils.DigestUtil;
import com.queen.core.tool.utils.Func;
import com.queen.system.user.entity.UserInfo;
import com.queen.system.user.feign.IUserClient;
import lombok.AllArgsConstructor;
import com.queen.auth.enums.QueenUserEnum;

import org.springframework.stereotype.Component;

/**
 * PasswordTokenGranter
 *
 * @author jensen
 */
@Component
@AllArgsConstructor
public class PasswordTokenGranter implements ITokenGranter {

    public static final String GRANT_TYPE = "password";

    private IUserClient userClient;

    @Override
    public UserInfo grant(TokenParameter tokenParameter) {
        String tenantId = tokenParameter.getArgs().getStr("tenantId");
        String account = tokenParameter.getArgs().getStr("account");
        String password = tokenParameter.getArgs().getStr("password");
        UserInfo userInfo = null;
        if (Func.isNoneBlank(account, password)) {
            // 获取用户类型
            String userType = tokenParameter.getArgs().getStr("userType");
            R<UserInfo> result;
            // 根据不同用户类型调用对应的接口返回数据，用户可自行拓展
            if (userType.equals(QueenUserEnum.WEB.getName())) {
                result = userClient.userInfo(tenantId, account, DigestUtil.encrypt(password));
            } else if (userType.equals(QueenUserEnum.APP.getName())) {
                result = userClient.userInfo(tenantId, account, DigestUtil.encrypt(password));
            } else {
                result = userClient.userInfo(tenantId, account, DigestUtil.encrypt(password));
            }
            userInfo = result.isSuccess() ? result.getData() : null;
        }
        return userInfo;
    }

}
