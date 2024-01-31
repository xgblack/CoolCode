package com.xgblack.cool.module.system.executor.query;

import com.xgblack.cool.framework.common.pojo.PageResult;
import com.xgblack.cool.module.system.convertor.UserConvertor;
import com.xgblack.cool.module.system.domain.gateway.UserGateway;
import com.xgblack.cool.module.system.dto.user.UserPageQry;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class UserPageQryExe {
    private final UserGateway gateway;

    public PageResult<UserCO> execute(UserPageQry qry) {
        return UserConvertor.INSTANCE.convertEntity2COPageResult(gateway.getPage(qry));
    }
}
