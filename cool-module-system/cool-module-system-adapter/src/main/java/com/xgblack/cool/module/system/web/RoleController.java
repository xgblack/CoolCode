package com.xgblack.cool.module.system.web;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.api.RoleServiceI;
import com.xgblack.cool.module.system.dto.permission.RoleAddCmd;
import com.xgblack.cool.module.system.dto.permission.RoleEditCmd;
import com.xgblack.cool.module.system.dto.permission.RoleEditStatusCmd;
import com.xgblack.cool.module.system.dto.permission.RolePageQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.RoleCO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 管理后台 - 角色
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Validated
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceI roleService;

    /**
     * 创建角色
     * @param cmd
     * @return
     */
    @PostMapping
    //@PreAuthorize("@ss.hasPermission('system:role:create')")
    public void add(@Valid @RequestBody RoleAddCmd cmd) {
        roleService.add(cmd);
    }

    /**
     * 修改角色
     * @param cmd
     */
    @PutMapping("/update")
    //@PreAuthorize("@ss.hasPermission('system:role:update')")
    public void edit(@Valid @RequestBody RoleEditCmd cmd) {
        roleService.edit(cmd);
    }

    /**
     * 修改角色状态
     * @param cmd
     */
    @PutMapping("/update-status")
    //@PreAuthorize("@ss.hasPermission('system:role:update')")
    public void editStatus(@Valid @RequestBody RoleEditStatusCmd cmd) {
        roleService.editStatus(cmd);
    }

    /**
     * 删除角色
     * @param id
     */
    @DeleteMapping("/delete")
    //@PreAuthorize("@ss.hasPermission('system:role:delete')")
    public void remove(@RequestParam("id") Long id) {
        roleService.remove(id);
    }

    /**
     * 获得角色信息
     * @param id
     * @return
     */
    @GetMapping("/get")
    //@PreAuthorize("@ss.hasPermission('system:role:query')")
    public RoleCO detail(@RequestParam("id") Long id) {
        return roleService.detail(id);
    }

    /**
     * 获得角色分页
     * @param qry
     * @return
     */
    @GetMapping("/page")
    //@PreAuthorize("@ss.hasPermission('system:role:query')")
    public PageResult<RoleCO> getRolePage(RolePageQry qry) {
        return roleService.page(qry);
    }

    /*@GetMapping({"/list-all-simple", "/simple-list"})
    @Operation(summary = "获取角色精简信息列表", description = "只包含被开启的角色，主要用于前端的下拉选项")
    public CommonResult<List<RoleSimpleRespVO>> getSimpleRoleList() {
        List<RoleDO> list = roleService.getRoleListByStatus(singleton(CommonStatusEnum.ENABLE.getStatus()));
        list.sort(Comparator.comparing(RoleDO::getSort));
        return success(BeanUtils.toBean(list, RoleSimpleRespVO.class));
    }*/

    /*@GetMapping("/export-excel")
    @Operation(summary = "导出角色 Excel")
    @OperateLog(type = EXPORT)
    @PreAuthorize("@ss.hasPermission('system:role:export')")
    public void export(HttpServletResponse response, @Validated RolePageReqVO exportReqVO) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoleDO> list = roleService.getRolePage(exportReqVO).getList();
        // 输出
        ExcelUtils.write(response, "角色数据.xls", "数据", RoleRespVO.class,
                BeanUtils.toBean(list, RoleRespVO.class));
    }*/

}
