package com.xgblack.cool.module.system.web;

import com.xgblack.cool.framework.common.pojo.PageResult;
import com.xgblack.cool.module.system.api.UserServiceI;
import com.xgblack.cool.module.system.dto.user.UserAddCmd;
import com.xgblack.cool.module.system.dto.user.UserEditCmd;
import com.xgblack.cool.module.system.dto.user.UserEditLockedCmd;
import com.xgblack.cool.module.system.dto.user.UserPageQry;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        userService.save(cmd);
    }

    /**
     * 修改用户
     * @param cmd
     */
    @PutMapping
    //@Operation(summary = "修改用户")
    //@PreAuthorize("@ss.hasPermission('system:user:update')")
    public void edit(@Validated @RequestBody UserEditCmd cmd) {
        userService.update(cmd);
    }

    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping
    //@Operation(summary = "删除用户")
    //@Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('system:user:delete')")
    public void delete(@RequestParam("id") Long id) {
        userService.remove(id);
    }

    /*@PutMapping("/update-password")
    //@Operation(summary = "重置用户密码")
    //@PreAuthorize("@ss.hasPermission('system:user:update-password')")
    public void updateUserPassword(@Valid @RequestBody UserUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(reqVO.getId(), reqVO.getPassword());
        return success(true);
    }*/

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

    /*@GetMapping({"/list-all-simple", "/simple-list"})
    //@Operation(summary = "获取用户精简信息列表", description = "只包含被开启的用户，主要用于前端的下拉选项")
    public List<UserSimpleRespVO> getSimpleUserList() {
        List<AdminUserDO> list = userService.getUserListByStatus(CommonStatusEnum.ENABLE.getStatus());
        // 拼接数据
        Map<Long, DeptDO> deptMap = deptService.getDeptMap(
                convertList(list, AdminUserDO::getDeptId));
        return success(UserConvert.INSTANCE.convertSimpleList(list, deptMap));
    }*/

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @GetMapping
    //@Operation(summary = "获得用户详情")
    //@Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('system:user:query')")
    public UserCO detail(@RequestParam("id") Long id) {
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
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AdminUserDO> list = userService.getUserPage(exportReqVO).getList();
        // 输出 Excel
        Map<Long, DeptDO> deptMap = deptService.getDeptMap(
                convertList(list, AdminUserDO::getDeptId));
        ExcelUtils.write(response, "用户数据.xls", "数据", UserRespVO.class,
                UserConvert.INSTANCE.convertList(list, deptMap));
    }*/

    /*@GetMapping("/get-import-template")
    //@Operation(summary = "获得导入用户模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<UserImportExcelVO> list = Arrays.asList(
                UserImportExcelVO.builder().username("yunai").deptId(1L).email("yunai@iocoder.cn").mobile("15601691300")
                        .nickname("芋道").status(CommonStatusEnum.ENABLE.getStatus()).sex(SexEnum.MALE.getSex()).build(),
                UserImportExcelVO.builder().username("yuanma").deptId(2L).email("yuanma@iocoder.cn").mobile("15601701300")
                        .nickname("源码").status(CommonStatusEnum.DISABLE.getStatus()).sex(SexEnum.FEMALE.getSex()).build()
        );
        // 输出
        ExcelUtils.write(response, "用户导入模板.xls", "用户列表", UserImportExcelVO.class, list);
    }*/

    /*@PostMapping("/import")
    //@Operation(summary = "导入用户")
    //@Parameters({
    //        @Parameter(name = "file", description = "Excel 文件", required = true),
    //        @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    //})
    //@PreAuthorize("@ss.hasPermission('system:user:import')")
    public UserImportRespVO importExcel(@RequestParam("file") MultipartFile file,
                                                      @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<UserImportExcelVO> list = ExcelUtils.read(file, UserImportExcelVO.class);
        return success(userService.importUserList(list, updateSupport));
    }*/
}
