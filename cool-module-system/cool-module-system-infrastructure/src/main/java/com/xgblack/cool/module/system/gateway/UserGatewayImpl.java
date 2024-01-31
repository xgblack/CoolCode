package com.xgblack.cool.module.system.gateway;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.update.UpdateChain;
import com.xgblack.cool.framework.common.pojo.PageResult;
import com.xgblack.cool.framework.security.dto.UserInfo;
import com.xgblack.cool.framework.security.service.RemoteUserService;
import com.xgblack.cool.module.system.convertor.UserConvertor;
import com.xgblack.cool.module.system.convertor.UserInfoConvertor;
import com.xgblack.cool.module.system.domain.gateway.UserGateway;
import com.xgblack.cool.module.system.domain.user.User;
import com.xgblack.cool.module.system.dto.user.UserEditLockedCmd;
import com.xgblack.cool.module.system.dto.user.UserPageQry;
import com.xgblack.cool.module.system.gateway.database.dataobject.UserDO;
import com.xgblack.cool.module.system.gateway.database.mapper.UserMapper;
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
        info.setSysUser(UserInfoConvertor.INSTANCE.convertDO2DTO(userDO));
        return info;
    }

    @Override
    public boolean lockUser(String username) {
        //TODO
        return false;
    }

    @Override
    public void create(User user) {
        userMapper.insertSelective(UserConvertor.INSTANCE.toDataObject(user));
    }

    @Override
    public User getById(Long id) {
        return UserConvertor.INSTANCE.convertDO2Entity(userMapper.selectOneById(id));
    }

    @Override
    public void update(User user) {
        userMapper.update(UserConvertor.INSTANCE.toDataObject(user));
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public PageResult<User> getPage(UserPageQry qry) {
        return UserConvertor.INSTANCE.convertDO2EntityPage(
                QueryChain.of(userMapper)
                        .from(USER)
                        .and(USER.USERNAME.like(qry.getUsername(), StrUtil.isNotBlank(qry.getUsername())))
                        .and(USER.PHONE.like(qry.getPhone(), StrUtil.isNotBlank(qry.getPhone())))
                        .and(USER.LOCKED.eq(qry.getLocked(), qry.getLocked() != null))
                        .and(USER.CREATE_TIME.between(qry.getCreateTime(), qry.getCreateTime() != null))
                        //TODO: 处理部门
                        .page(qry.buildPage())
        );

    }

    @Override
    public void updateUserLocked(UserEditLockedCmd cmd) {
        UpdateChain.of(userMapper)
                .set(USER.LOCKED, cmd.getLocked())
                .where(USER.ID.eq(cmd.getId()))
                .update();
    }
}
