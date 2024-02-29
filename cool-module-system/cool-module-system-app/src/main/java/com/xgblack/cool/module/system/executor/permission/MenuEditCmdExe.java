package com.xgblack.cool.module.system.executor.permission;

import com.xgblack.cool.module.system.convertor.MenuConvertor;
import com.xgblack.cool.module.system.domain.gateway.MenuGateway;
import com.xgblack.cool.module.system.dto.permission.MenuEditCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuEditCmdExe {

    private final MenuGateway gateway;

    public void execute(MenuEditCmd cmd) {
        gateway.update(MenuConvertor.INSTANCE.toEntity(cmd));
    }
}
