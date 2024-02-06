package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.update.UpdateChain;
import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.framework.security.dto.SysUser;
import com.xgblack.cool.framework.security.dto.UserInfo;
import com.xgblack.cool.framework.security.service.RemoteUserService;
import com.xgblack.cool.module.system.convertor.UserConvertor;
import com.xgblack.cool.module.system.domain.gateway.UserGateway;
import com.xgblack.cool.module.system.domain.user.User;
import com.xgblack.cool.module.system.dto.user.UserEditLockedCmd;
import com.xgblack.cool.module.system.dto.user.UserPageQry;
import com.xgblack.cool.module.system.gateway.database.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
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
        SysUser sysUser = QueryChain.of(userMapper)
                .from(USER)
                .and(USER.USERNAME.eq(username, StrUtil.isNotBlank(username))) //可以用where也可以直接用and
                .and(USER.PHONE.eq(phone, StrUtil.isNotBlank(phone)))
                .oneAs(SysUser.class);

        // TODO:设置角色列表 （ID）
        // TODO:设置权限列表（menu.permission）
        UserInfo info = new UserInfo();
        info.setSysUser(sysUser);
        return info;
    }

    @Override
    public boolean lockUser(String username) {
        return UpdateChain.of(userMapper)
                .set(USER.LOCKED, true)
                .where(USER.USERNAME.eq(username))
                .update();
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
        return PageResult.of(
                QueryChain.of(userMapper)
                        .from(USER)
                        .and(USER.USERNAME.like(qry.getUsername(), StrUtil.isNotBlank(qry.getUsername())))
                        .and(USER.PHONE.like(qry.getPhone(), StrUtil.isNotBlank(qry.getPhone())))
                        .and(USER.LOCKED.eq(qry.getLocked(), qry.getLocked() != null))
                        .and(USER.CREATE_TIME.between(qry.getCreateTime(), qry.getCreateTime() != null))
                        //TODO: 处理部门
                        .orderBy(USER.ID.desc())
                        .pageAs(qry.buildPage(),User.class)
        );

    }

    @Override
    public void updateUserLocked(UserEditLockedCmd cmd) {
        UpdateChain.of(userMapper)
                .set(USER.LOCKED, cmd.getLocked())
                .where(USER.ID.eq(cmd.getId()))
                .update();
    }

    @Override
    public void updatePassword(Long id, String password) {
        UpdateChain.of(userMapper)
                .set(USER.PASSWORD, password)
                .where(USER.ID.eq(id))
                .update();
    }
}
