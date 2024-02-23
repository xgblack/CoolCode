package com.xgblack.cool.module.system.web;

import com.xgblack.cool.framework.common.utils.response.CoolThrowable;
import com.xgblack.cool.framework.security.utils.SecurityUtils;
import com.xgblack.cool.module.system.api.UserServiceI;
import com.xgblack.cool.module.system.dto.user.UserProfileEditCmd;
import com.xgblack.cool.module.system.dto.user.UserProfileEditPasswordCmd;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;
import com.xgblack.cool.module.system.dto.user.clientobject.UserProfileDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dromara.hutool.core.lang.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 管理后台 - 用户个人中心
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Validated
@RestController
@RequestMapping("/system/user/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserServiceI userService;

    /**
     * 获取当前用户全部信息
     * @return UserProfileDTO 当前用户信息
     */
    @GetMapping
    public UserProfileDTO info() {
        UserCO userCO = userService.getDetail(getLoginUserId());
        /*AdminUserDO user = userService.getUser(id);
        // 拼接数据
        DeptDO dept = deptService.getDept(user.getDeptId());
        return success(UserConvert.INSTANCE.convert(user, dept));*/
        //TODO
        return null;
    }

    /**
     * 修改用户个人信息
     * @param cmd
     * @return
     */
    @PutMapping
    //@Operation(summary = "修改用户个人信息")
    public void updateUserProfile(@Valid @RequestBody UserProfileEditCmd cmd) {
        userService.editUserProfile(getLoginUserId(), cmd);
    }

    /**
     * 修改用户个人密码
     * @param cmd
     */
    @PutMapping("/update-password")
    //@Operation(summary = "修改用户个人密码")
    public void updateUserProfilePassword(@Valid @RequestBody UserProfileEditPasswordCmd cmd) {
        userService.editUserPassword(getLoginUserId(), cmd.getOldPassword(), cmd.getNewPassword());
    }

    /**
     * 上传用户个人头像
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update-avatar", method = {RequestMethod.POST, RequestMethod.PUT}) // 解决 uni-app 不支持 Put 上传文件的问题
    //@Operation(summary = "上传用户个人头像")
    public String updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        /*if (file.isEmpty()) {
            throw exception(FILE_IS_EMPTY);
        }
        String avatar = userService.updateUserAvatar(getLoginUserId(), file.getInputStream());*/
        return null;
    }

    private Long getLoginUserId() {
        Long loginUserId = SecurityUtils.getLoginUserId();
        CoolThrowable.wrapAssert(() -> Assert.notNull(loginUserId, "未登录用户不能修改个人信息"));
        return loginUserId;
    }

}
