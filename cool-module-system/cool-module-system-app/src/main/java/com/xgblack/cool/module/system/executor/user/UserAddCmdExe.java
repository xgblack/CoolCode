package com.xgblack.cool.module.system.executor.user;

import com.xgblack.cool.framework.security.utils.PasswdUtils;
import com.xgblack.cool.module.system.convertor.UserConvertor;
import com.xgblack.cool.module.system.domain.gateway.UserGateway;
import com.xgblack.cool.module.system.dto.user.UserAddCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAddCmdExe {

    private final UserGateway gateway;

    public Long execute(UserAddCmd cmd) {
        cmd.setPassword(PasswdUtils.decodeAndEncryptPassword(cmd.getPassword()));

        return gateway.create(UserConvertor.INSTANCE.toEntity(cmd));
    }
}
