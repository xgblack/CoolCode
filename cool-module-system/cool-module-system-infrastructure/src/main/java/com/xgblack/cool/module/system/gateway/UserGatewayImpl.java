package com.xgblack.cool.module.system.gateway;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryChain;
import com.xgblack.cool.module.system.convertor.UserInfoConvert;
import com.xgblack.cool.module.system.domain.gateway.UserGateway;
import com.xgblack.cool.module.system.gateway.database.dataobject.UserDO;
import com.xgblack.cool.module.system.gateway.database.mapper.UserMapper;
import com.xgblack.framework.security.dto.UserInfo;
import com.xgblack.framework.security.service.RemoteUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.xgblack.cool.module.system.gateway.database.dataobject.table.UserTableDef.USER;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway, RemoteUserService {

    private final UserMapper userMapper;

    @Override
    public UserInfo info(String username, String phone) {
        UserDO userDO = QueryChain.of(userMapper)
                .from(USER)
                .and(USER.USERNAME.eq(username, StrUtil.isNotBlank(username))) //可以用where也可以直接用and
                .and(USER.PHONE.eq(phone, StrUtil.isNotBlank(phone)))
                .one();

        // TODO:设置角色列表 （ID）
        // TODO:设置权限列表（menu.permission）
        UserInfo info = new UserInfo();
        info.setSysUser(UserInfoConvert.INSTANCE.convertDO2DTO(userDO));
        return info;
    }

    @Override
    public boolean lockUser(String username) {
        //TODO
        return false;
    }
}
