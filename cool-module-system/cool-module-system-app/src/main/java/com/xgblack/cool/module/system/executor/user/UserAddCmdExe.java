package com.xgblack.cool.module.system.executor.user;

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


    public void execute(UserAddCmd cmd) {
        //TODO
        //gateway.create(StudentConvertor.INSTANCE.toEntity(cmd));
    }
}
