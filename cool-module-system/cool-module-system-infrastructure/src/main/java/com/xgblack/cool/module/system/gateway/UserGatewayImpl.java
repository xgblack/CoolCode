package com.xgblack.cool.module.system.gateway;

import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.update.UpdateChain;
import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.framework.security.dto.SysUser;
import com.xgblack.cool.framework.security.dto.UserInfo;
import com.xgblack.cool.framework.security.service.RemoteUserService;
import com.xgblack.cool.module.system.convertor.UserConvertor;
import com.xgblack.cool.module.system.domain.company.dept.Dept;
import com.xgblack.cool.module.system.domain.gateway.DeptGateway;
import com.xgblack.cool.module.system.domain.gateway.PermissionGateway;
import com.xgblack.cool.module.system.domain.gateway.UserGateway;
import com.xgblack.cool.module.system.domain.user.User;
import com.xgblack.cool.module.system.dto.user.UserEditLockedCmd;
import com.xgblack.cool.module.system.dto.user.UserPageQry;
import com.xgblack.cool.module.system.dto.user.UserProfileEditCmd;
import com.xgblack.cool.module.system.gateway.database.dataobject.MenuDO;
import com.xgblack.cool.module.system.gateway.database.dataobject.UserDO;
import com.xgblack.cool.module.system.gateway.database.mapper.MenuMapper;
import com.xgblack.cool.module.system.gateway.database.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.xgblack.cool.module.system.gateway.database.dataobject.table.UserTableDef.USER;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway, RemoteUserService {

    private final UserMapper userMapper;

    private final PermissionGateway permissionGateway;

    private final MenuMapper menuMapper;

    private final DeptGateway deptGateway;

    private final static UserConvertor convertor = UserConvertor.INSTANCE;

    @Override
    public UserInfo info(String username, String phone) {
        SysUser sysUser = QueryChain.of(userMapper)
                .from(USER)
                .and(USER.USERNAME.eq(username, StrUtil.isNotBlank(username))) //可以用where也可以直接用and
                .and(USER.PHONE.eq(phone, StrUtil.isNotBlank(phone)))
                .oneAs(SysUser.class);

        UserInfo info = new UserInfo();
        // 设置角色列表 （ID）
        Set<Long> roleIds = permissionGateway.getRoleIdsByUserId(sysUser.getId());
        // 权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        Set<Long> menuIds = new HashSet<>();
        roleIds.forEach(roleId -> {
            Set<Long> menuIdSet = permissionGateway.getMenuIdsByRoleId(roleId);
            menuIds.addAll(menuIdSet);
        });

        if (CollUtil.isNotEmpty(menuIds)) {
            List<MenuDO> menuDOS = menuMapper.selectListByIds(menuIds);
            permissions = menuDOS.stream().map(MenuDO::getPermission).collect(Collectors.toSet());
        }
        info.setSysUser(sysUser)
                .setRoles(roleIds)
                .setPermissions(permissions);

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
    public UserInfo info(Long userId) {
        SysUser sysUser = userMapper.selectOneWithRelationsByIdAs(userId, SysUser.class);
        if (sysUser == null) {
            log.error("用户不存在,用户id={}", userId);
            return null;
        }
        UserInfo info = new UserInfo();
        // 设置角色列表 （ID）
        Set<Long> roleIds = permissionGateway.getRoleIdsByUserId(sysUser.getId());
        // 权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        Set<Long> menuIds = new HashSet<>();
        roleIds.forEach(roleId -> {
            Set<Long> menuIdSet = permissionGateway.getMenuIdsByRoleId(roleId);
            menuIds.addAll(menuIdSet);
        });

        if (CollUtil.isNotEmpty(menuIds)) {
            List<MenuDO> menuDOS = menuMapper.selectListByIds(menuIds);
            permissions = menuDOS.stream().map(MenuDO::getPermission).collect(Collectors.toSet());
        }
        info.setSysUser(sysUser)
                .setRoles(roleIds)
                .setPermissions(permissions);

        return info;
    }

    @Override
    public Long create(User user) {
        UserDO userDO = convertor.toDataObject(user);
        userMapper.insertSelective(userDO);
        return userDO.getId();
    }

    @Override
    public User getById(Long id) {
        return convertor.convertDO2Entity(userMapper.selectOneById(id));
    }

    @Override
    public void update(User user) {
        userMapper.update(convertor.toDataObject(user));
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public PageResult<User> getPage(UserPageQry qry) {
        Set<Long> deptIds = new HashSet<>();
        if (Objects.nonNull(qry.getDeptId())) {
            List<Dept> childDeptList = deptGateway.getChildDeptList(qry.getDeptId());
            Set<Long> ids = childDeptList.stream().map(Dept::getId).collect(Collectors.toSet());
            deptIds.addAll(ids);
        }
        deptIds.add(qry.getDeptId()); // 包括自身
        return PageResult.of(
                QueryChain.of(userMapper)
                        .from(USER)
                        .and(USER.USERNAME.like(qry.getUsername(), StrUtil.isNotBlank(qry.getUsername())))
                        .and(USER.PHONE.like(qry.getPhone(), StrUtil.isNotBlank(qry.getPhone())))
                        .and(USER.LOCKED.eq(qry.getLocked(), qry.getLocked() != null))
                        .and(USER.CREATE_TIME.between(qry.getCreateTime(), qry.getCreateTime() != null))
                        .and(USER.DEPT_ID.in(deptIds)) // 处理部门 同时筛选子部门
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

    @Override
    public void updateUserProfile(Long id, UserProfileEditCmd cmd) {
        UpdateChain.of(userMapper)
                .set(USER.NICKNAME, cmd.getNickname(), StrUtil.isNotBlank(cmd.getNickname()))
                .set(USER.EMAIL, cmd.getEmail(), StrUtil.isNotBlank(cmd.getEmail()))
                .set(USER.PHONE, cmd.getPhone(), StrUtil.isNotBlank(cmd.getPhone()))
                .set(USER.SEX, cmd.getSex(), cmd.getSex() != null)
                .where(USER.ID.eq(id))
                .update();
    }
}
