package com.xgblack.cool.module.system.executor.user.query;

import com.xgblack.cool.module.system.convertor.DeptConvertor;
import com.xgblack.cool.module.system.convertor.PostConvertor;
import com.xgblack.cool.module.system.convertor.RoleConvertor;
import com.xgblack.cool.module.system.convertor.UserConvertor;
import com.xgblack.cool.module.system.domain.company.dept.Dept;
import com.xgblack.cool.module.system.domain.company.post.Post;
import com.xgblack.cool.module.system.domain.gateway.*;
import com.xgblack.cool.module.system.domain.permission.Role;
import com.xgblack.cool.module.system.domain.user.User;
import com.xgblack.cool.module.system.dto.user.clientobject.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class UserProfileInfoQryExe {

    private final UserGateway userGateway;
    private final PermissionGateway permissionService;
    private final RoleGateway roleGateway;
    private final DeptGateway deptGateway;
    private final PostGateway postGateway;



    public UserProfileDTO execute(Long loginUserId) {
        // 获得用户基本信息
        User user = userGateway.getById(loginUserId);
        // 获得用户角色
        List<Role> roles = roleGateway.getRolesByIds(permissionService.getRoleIdsByUserId(user.getId()));
        // 获得部门信息
        Dept dept = Objects.nonNull(user.getDeptId()) ? deptGateway.getById(user.getDeptId()) : null;
        // 获得岗位信息
        List<Post> posts = CollUtil.isNotEmpty(user.getPostIds()) ? postGateway.getPostsByIds(user.getPostIds()) : null;
        // TODO 获得社交用户信息
        //List<SocialUserDO> socialUsers = socialService.getSocialUserList(user.getId(), UserTypeEnum.ADMIN.getValue());

        return UserConvertor.INSTANCE.convertEntity2DTO(user)
                .setRoles(RoleConvertor.INSTANCE.convertEntity2SimpleCOList(roles))
                .setDept(DeptConvertor.INSTANCE.convertEntity2SimpleCO(dept))
                .setPosts(PostConvertor.INSTANCE.convertEntity2SimpleCOList(posts));
    }
}
