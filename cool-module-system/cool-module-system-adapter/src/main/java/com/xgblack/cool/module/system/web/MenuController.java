package com.xgblack.cool.module.system.web;

import com.xgblack.cool.module.system.api.MenuServiceI;
import com.xgblack.cool.module.system.dto.permission.MenuAddCmd;
import com.xgblack.cool.module.system.dto.permission.MenuEditCmd;
import com.xgblack.cool.module.system.dto.permission.MenuListQry;
import com.xgblack.cool.module.system.dto.permission.clientobject.MenuCO;
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
    public void add(@Valid @RequestBody MenuAddCmd cmd) {
        menuService.add(cmd);
    }

    /**
     * 修改菜单
     * @param cmd
     */
    @PutMapping
    //@PreAuthorize("@ss.hasPermission('system:menu:edit')")
    public void edit(@Valid @RequestBody MenuEditCmd cmd) {
        menuService.edit(cmd);
    }

    /**
     * 删除菜单
     * @param id
     */
    @DeleteMapping("{id}")
    //@Parameter(name = "id", description = "菜单编号", required= true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('system:menu:remove')")
    public void remove(@PathVariable("id") Long id) {
        menuService.remove(id);
    }

    @GetMapping("list")
    //@Operation(summary = "获取菜单列表", description = "用于【菜单管理】界面")
    //@PreAuthorize("@ss.hasPermission('system:menu:query')")
    public List<MenuCO> list(MenuListQry qry) {
        return menuService.list(qry);
    }

    /*@GetMapping({"/list-all-simple", "simple-list"})
    //@Operation(summary = "获取菜单精简信息列表", description = "只包含被开启的菜单，用于【角色分配菜单】功能的选项。" + "在多租户的场景下，会只返回租户所在套餐有的菜单")
    public List<MenuSimpleCO> getSimpleList() {
        //TODO
        List<MenuDO> list = menuService.getMenuListByTenant( new MenuListReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
    }*/

    @GetMapping("detail/{id}")
    //@Operation(summary = "获取菜单信息")
    //@PreAuthorize("@ss.hasPermission('system:menu:query')")
    public MenuCO detail(@PathVariable Long id) {
        return menuService.detail(id);
    }
}
