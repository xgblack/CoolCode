package com.xgblack.cool.module.system.web;

import com.mzt.logapi.starter.annotation.LogRecord;
import com.xgblack.cool.module.system.api.DeptServiceI;
import com.xgblack.cool.module.system.common.constans.ModuleType;
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
    //@PreAuthorize("@ss.hasPermission('system:dept:create')")
    @LogRecord(success = "创建部门, 名称「{{#cmd.name}}」",
            fail = "创建部门失败，失败原因：「{{#_errorMsg}}」",
            type = ModuleType.DEPT, bizNo = "{{#_ret}}")
    public Long add(@Valid @RequestBody DeptAddCmd cmd) {
        return deptService.add(cmd);
    }

    /**
     * 修改部门
     * @param cmd
     */
    @PutMapping
    //@PreAuthorize("@ss.hasPermission('system:dept:update')")
    @LogRecord(success = "修改部门, 名称「{{#cmd.name}}」",
            fail = "修改部门失败，失败原因：「{{#_errorMsg}}」",
            extra = "{{#cmd.toJson()}}",
            type = ModuleType.DEPT, bizNo = "{{#cmd.id}}")
    public void edit(@Valid @RequestBody DeptEditCmd cmd) {
        deptService.edit(cmd);
    }

    /**
     * 删除部门
     * @param id
     */
    @DeleteMapping("{id}")
    //@PreAuthorize("@ss.hasPermission('system:dept:delete')")
    @LogRecord(success = "删除部门, 编号「{{#id}}」",
            fail = "删除部门失败，失败原因：「{{#_errorMsg}}」",
            type = ModuleType.DEPT, bizNo = "{{#id}}")
    public void remove(@PathVariable("id") Long id) {
        deptService.remove(id);
    }

    /**
     * 获取部门列表
     * @param qry
     * @return
     */
    @GetMapping("/list")
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
    //@PreAuthorize("@ss.hasPermission('system:dept:query')")
    public DeptCO detail(@PathVariable("id") Long id) {
        return deptService.detail(id);
    }

}
