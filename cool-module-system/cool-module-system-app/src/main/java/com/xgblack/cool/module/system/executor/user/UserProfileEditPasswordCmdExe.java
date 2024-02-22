package com.xgblack.cool.module.system.executor.user;

import com.xgblack.cool.framework.common.pojo.dto.Command;
import com.xgblack.cool.framework.common.utils.response.CoolThrowable;
import com.xgblack.cool.framework.security.utils.PasswdUtils;
import com.xgblack.cool.module.system.domain.gateway.UserGateway;
import com.xgblack.cool.module.system.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.lang.Assert;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class UserProfileEditPasswordCmdExe extends Command {

    private final UserGateway gateway;


    public void execute(Long id, String oldPassword, String newPassword) {
        User user = gateway.getById(id);
        CoolThrowable.wrapAssert(() -> Assert.notNull(user, "用户不存在"));

        String password = PasswdUtils.decode(oldPassword);
        CoolThrowable.wrapAssert(() -> Assert.isTrue(PasswdUtils.matches(password, user.getPassword()), "密码错误"));
        //FIXME: 是否增加密码错误次数校验

        gateway.updatePassword(id, PasswdUtils.decodeAndEncryptPassword(newPassword));

    }
}
