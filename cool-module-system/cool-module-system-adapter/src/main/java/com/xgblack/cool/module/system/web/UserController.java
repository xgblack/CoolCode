package com.xgblack.cool.module.system.web;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.api.UserServiceI;
import com.xgblack.cool.module.system.dto.user.*;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;
import com.xgblack.cool.module.system.dto.user.clientobject.UserSimpleDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 管理后台 - 用户
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Validated
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceI userService;

    /**
     * 新增用户
     * @param cmd 用户信息
     */
    @PostMapping
    //@PreAuthorize("@ss.hasPermission('system:user:create')")
    public void add(@Validated @RequestBody UserAddCmd cmd) {
        userService.add(cmd);
    }

    /**
     * 修改用户
     * @param cmd
     */
    @PutMapping
    //@Operation(summary = "修改用户")
    //@PreAuthorize("@ss.hasPermission('system:user:update')")
    public void edit(@Validated @RequestBody UserEditCmd cmd) {
        userService.edit(cmd);
    }

    /**
     * 删除用户
     * @param id 用户id
     */
    @DeleteMapping("{id}")
    //@Operation(summary = "删除用户")
    //@PreAuthorize("@ss.hasPermission('system:user:delete')")
    public void delete(@PathVariable Long id) {
        userService.remove(id);
    }

    /**
     * 修改用户密码
     * @param cmd
     */
    @PutMapping("/update-password")
    //@Operation(summary = "重置用户密码")
    //@PreAuthorize("@ss.hasPermission('system:user:update-password')")
    public void editPassword(@Validated @RequestBody UserEditPasswordCmd cmd) {
        userService.editPassword(cmd);
    }

    /**
     * 修改用户锁定状态
     * @param cmd
     */
    @PutMapping("/update-locked")
    //@Operation(summary = "修改用户状态")
    //@PreAuthorize("@ss.hasPermission('system:user:update')")
    public void editLocked(@Validated @RequestBody UserEditLockedCmd cmd) {
        userService.editLocked(cmd);
    }

    /**
     * 分页查询
     * @param qry
     * @return
     */
    @GetMapping("/page")
    //@Operation(summary = "获得用户分页列表")
    //@PreAuthorize("@ss.hasPermission('system:user:list')")
    public PageResult<UserCO> page(@Validated UserPageQry qry) {
        // 获得用户分页列表
        /*PageResult<AdminUserDO> pageResult = userService.getUserPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }
        // 拼接数据
        Map<Long, DeptDO> deptMap = deptService.getDeptMap(
                convertList(pageResult.getList(), AdminUserDO::getDeptId));
        return success(new PageResult<>(UserConvert.INSTANCE.convertList(pageResult.getList(), deptMap),
                pageResult.getTotal()));*/
        return userService.getPage(qry);
    }

    /**
     * 获取用户精简信息列表
     * @return
     */
    @GetMapping({"/list-all-simple", "/simple-list"})
    //@Operation(summary = "获取用户精简信息列表", description = "只包含被开启的用户，主要用于前端的下拉选项")
    public List<UserSimpleDTO> getSimpleUserList() {
        //TODO
        //List<AdminUserDO> list = userService.getUserListByStatus(CommonStatusEnum.ENABLE.getStatus());
        // 拼接数据
        //Map<Long, DeptDO> deptMap = deptService.getDeptMap( convertList(list, AdminUserDO::getDeptId));
        return null;
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @GetMapping("detail/{id}")
    //@Operation(summary = "获得用户详情")
    //@Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('system:user:query')")
    public UserCO detail(@PathVariable("id") Long id) {
        /*AdminUserDO user = userService.getUser(id);
        // 拼接数据
        DeptDO dept = deptService.getDept(user.getDeptId());
        return success(UserConvert.INSTANCE.convert(user, dept));*/
        return userService.getDetail(id);
    }


    /*@GetMapping("/export")
    //@Operation(summary = "导出用户")
    //@PreAuthorize("@ss.hasPermission('system:user:export')")
    //@OperateLog(type = EXPORT)
    public void exportUserList(@Validated UserPageReqVO exportReqVO,
                               HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageQuery.PAGE_SIZE_NONE);
        List<AdminUserDO> list = userService.getUserPage(exportReqVO).getList();
        // 输出 Excel
        Map<Long, DeptDO> deptMap = deptService.getDeptMap(
                convertList(list, AdminUserDO::getDeptId));
        ExcelUtils.write(response, "用户数据.xls", "数据", UserRespVO.class,
                UserConvert.INSTANCE.convertList(list, deptMap));
    }*/

    /**
     * 获得导入用户模板
     */
    @GetMapping("/get-import-template")
    //@Operation(summary = "获得导入用户模板")
    public void importTemplate(HttpServletResponse response) {
        // 手动创建导出 demo
        /*List<UserImportExcelVO> list = Arrays.asList(
                UserImportExcelVO.builder().username("yunai").deptId(1L).email("yunai@iocoder.cn").mobile("15601691300")
                        .nickname("芋道").status(CommonStatusEnum.ENABLE.getStatus()).sex(SexEnum.MALE.getSex()).build(),
                UserImportExcelVO.builder().username("yuanma").deptId(2L).email("yuanma@iocoder.cn").mobile("15601701300")
                        .nickname("源码").status(CommonStatusEnum.DISABLE.getStatus()).sex(SexEnum.FEMALE.getSex()).build()
        );
        // 输出
        ExcelUtils.write(response, "用户导入模板.xls", "用户列表", UserImportExcelVO.class, list);*/
    }

    /**
     * 导入用户
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @PostMapping("/import")
    //@PreAuthorize("@ss.hasPermission('system:user:import')")
    public Object importExcel(@RequestParam("file") MultipartFile file, @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        /*List<UserImportExcelVO> list = ExcelUtils.read(file, UserImportExcelVO.class);
        return success(userService.importUserList(list, updateSupport));*/
        return null;
    }
}
