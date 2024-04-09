package com.xgblack.cool.module.system.web;

import com.mzt.logapi.starter.annotation.LogRecord;
import com.xgblack.cool.module.system.api.MenuServiceI;
import com.xgblack.cool.module.system.common.constans.ModuleType;
import com.xgblack.cool.module.system.dto.permission.MenuAddCmd;
import com.xgblack.cool.module.system.dto.permission.MenuEditCmd;
import com.xgblack.cool.module.system.dto.permission.MenuListQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuCO;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuSimpleCO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理后台 - 菜单
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */


@RestController
@RequestMapping("/system/menu")
@Validated
@RequiredArgsConstructor
public class MenuController {

    private final MenuServiceI menuService;

    /**
     * 创建菜单
     * @param cmd
     */
    @PostMapping
    //@PreAuthorize("@ss.hasPermission('system:menu:add')")
    @LogRecord(success = "创建菜单, 名称「{{#cmd.name}}」",
            fail = "创建菜单失败，失败原因：「{{#_errorMsg}}」",
            type = ModuleType.MENU, bizNo = "{{#_ret}}")
    public Long add(@Valid @RequestBody MenuAddCmd cmd) {
        return menuService.add(cmd);
    }

    /**
     * 修改菜单
     * @param cmd
     */
    @PutMapping
    //@PreAuthorize("@ss.hasPermission('system:menu:edit')")
    @LogRecord(success = "修改菜单, 名称「{{#cmd.name}}」",
            fail = "修改菜单失败，失败原因：「{{#_errorMsg}}」",
            extra = "{{#cmd.toJson()}}",
            type = ModuleType.MENU, bizNo = "{{#cmd.id}}")
    public void edit(@Valid @RequestBody MenuEditCmd cmd) {
        menuService.edit(cmd);
    }

    /**
     * 删除菜单
     * @param id
     */
    @DeleteMapping("{id}")
    //@PreAuthorize("@ss.hasPermission('system:menu:remove')")
    @LogRecord(success = "删除菜单, 编号「{{#id}}」",
            fail = "删除菜单失败，失败原因：「{{#_errorMsg}}」",
            type = ModuleType.MENU, bizNo = "{{#id}}")
    public void remove(@PathVariable("id") Long id) {
        menuService.remove(id);
    }

    /**
     * 获取菜单列表
     * <p>用于【菜单管理】界面
     * @param qry
     * @return
     */
    @GetMapping("list")
    //@Operation(summary = "获取菜单列表", description = "用于【菜单管理】界面")
    //@PreAuthorize("@ss.hasPermission('system:menu:query')")
    public List<MenuCO> list(MenuListQry qry) {
        return menuService.list(qry);
    }

    /**
     * 获取菜单精简信息列表
     * <p>
     * 只包含被开启的菜单，用于【角色分配菜单】功能的选项。
     * 在多租户的场景下，会只返回租户所在套餐有的菜单
     * @return
     */
    @GetMapping({"/list-all-simple", "simple-list"})
    public List<MenuSimpleCO> getSimpleList() {
        return menuService.getSimpleListByTenant();
    }

    @GetMapping("detail/{id}")
    //@PreAuthorize("@ss.hasPermission('system:menu:query')")
    public MenuCO detail(@PathVariable Long id) {
        return menuService.detail(id);
    }
}
