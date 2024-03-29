package com.xgblack.cool.module.system.web;

import com.xgblack.cool.module.system.api.DeptServiceI;
import com.xgblack.cool.module.system.dto.company.dept.DeptAddCmd;
import com.xgblack.cool.module.system.dto.company.dept.DeptEditCmd;
import com.xgblack.cool.module.system.dto.company.dept.DeptListQry;
import com.xgblack.cool.module.system.dto.company.dept.clientobject.DeptCO;
import com.xgblack.cool.module.system.dto.company.dept.clientobject.DeptSimpleCO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理后台 - 部门
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@RestController
@RequestMapping("/system/dept")
@Validated
@RequiredArgsConstructor
public class DeptController {

    private final DeptServiceI deptService;

    /**
     * 创建部门
     * @param cmd
     * @return
     */
    @PostMapping
    //@Operation(summary = "创建部门")
    //@PreAuthorize("@ss.hasPermission('system:dept:create')")
    public Long add(@Valid @RequestBody DeptAddCmd cmd) {
        return deptService.add(cmd);
    }

    /**
     * 更新部门
     * @param cmd
     */
    @PutMapping
    //@Operation(summary = "更新部门")
    //@PreAuthorize("@ss.hasPermission('system:dept:update')")
    public void edit(@Valid @RequestBody DeptEditCmd cmd) {
        deptService.edit(cmd);
    }

    /**
     * 删除部门
     * @param id
     */
    @DeleteMapping("{id}")
    //@Operation(summary = "删除部门")
    //@Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('system:dept:delete')")
    public void remove(@PathVariable("id") Long id) {
        deptService.remove(id);
    }

    /**
     * 获取部门列表
     * @param qry
     * @return
     */
    @GetMapping("/list")
    //@Operation(summary = "获取部门列表")
    //@PreAuthorize("@ss.hasPermission('system:dept:query')")
    public List<DeptCO> getDeptList(DeptListQry qry) {
        return deptService.list(qry);
    }

    /**
     * 获取部门精简信息列表
     * @return
     */
    @GetMapping(value = {"/list-all-simple", "/simple-list"})
    //@Operation(summary = "获取部门精简信息列表", description = "只包含被开启的部门，主要用于前端的下拉选项")
    public List<DeptSimpleCO> getSimpleList() {
        return deptService.getSimpleList();
    }

    /**
     * 获得部门信息
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    //@Operation(summary = "获得部门信息")
    //@Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('system:dept:query')")
    public DeptCO detail(@PathVariable("id") Long id) {
        return deptService.detail(id);
    }

}
