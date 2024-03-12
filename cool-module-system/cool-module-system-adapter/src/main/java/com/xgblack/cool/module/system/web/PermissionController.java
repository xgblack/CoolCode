package com.xgblack.cool.module.system.web;

import com.xgblack.cool.module.system.api.PermissionServiceI;
import com.xgblack.cool.module.system.dto.permission.PermissionRoleDataScopeAssignCmd;
import com.xgblack.cool.module.system.dto.permission.PermissionRoleMenuAssignCmd;
import com.xgblack.cool.module.system.dto.permission.PermissionUserRoleAssignCmd;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 管理后台 - 权限
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@RestController
@RequestMapping("/system/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionServiceI permissionService;

    /**
     * 获得角色拥有的菜单编号
     * @param roleId
     * @return
     */
    //@Parameter(name = "roleId", description = "角色编号", required = true)
    @GetMapping("/list-role-menus")
    //@PreAuthorize("@ss.hasPermission('system:permission:assign-role-menu')")
    public Set<Long> getRoleMenuList(Long roleId) {
        return permissionService.getRoleMenuListByRoleId(roleId);
    }

    /**
     * 赋予角色菜单
     * @param cmd
     */
    @PostMapping("/assign-role-menu")
    //@Operation(summary = "赋予角色菜单")
    //@PreAuthorize("@ss.hasPermission('system:permission:assign-role-menu')")
    public void assignRoleMenu(@Validated @RequestBody PermissionRoleMenuAssignCmd cmd) {
        // TODO: 开启多租户的情况下，需要过滤掉未开通的菜单
        //tenantService.handleTenantMenu(menuIds -> reqVO.getMenuIds().removeIf(menuId -> !CollUtil.contains(menuIds, menuId)));
        // 执行菜单的分配
        permissionService.assignRoleMenu(cmd);
    }

    /**
     * 赋予角色数据权限
     * @param cmd
     */
    @PostMapping("/assign-role-data-scope")
    //@Operation(summary = "赋予角色数据权限")
    //@PreAuthorize("@ss.hasPermission('system:permission:assign-role-data-scope')")
    public void assignRoleDataScope(@Valid @RequestBody PermissionRoleDataScopeAssignCmd cmd) {
        permissionService.assignRoleDataScope(cmd);
    }

    /**
     * 获得管理员拥有的角色编号列表
     * @param userId
     * @return
     */
    //@Parameter(name = "userId", description = "用户编号", required = true)
    @GetMapping("/list-user-roles")
    //@PreAuthorize("@ss.hasPermission('system:permission:assign-user-role')")
    public Set<Long> listAdminRoles(@RequestParam("userId") Long userId) {
        return permissionService.queryUserRoleIdsByUserId(userId);
    }

    /**
     * 赋予用户角色
     * @param cmd
     */
    @PostMapping("/assign-user-role")
    //@PreAuthorize("@ss.hasPermission('system:permission:assign-user-role')")
    public void assignUserRole(@Validated @RequestBody PermissionUserRoleAssignCmd cmd) {
        permissionService.assignUserRole(cmd);
    }

}
