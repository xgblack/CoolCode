package com.xgblack.cool.module.system.service;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.api.UserServiceI;
import com.xgblack.cool.module.system.dto.user.*;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;
import com.xgblack.cool.module.system.dto.user.clientobject.UserProfileDTO;
import com.xgblack.cool.module.system.executor.user.*;
import com.xgblack.cool.module.system.executor.user.query.UserByIdQryExe;
import com.xgblack.cool.module.system.executor.user.query.UserPageQryExe;
import com.xgblack.cool.module.system.executor.user.query.UserProfileInfoQryExe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceI {

    private final UserAddCmdExe userAddCmdExe;
    private final UserByIdQryExe userByIdQryExe;
    private final UserRemoveCmdExe userRemoveCmdExe;
    private final UserEditCmdExe userEditCmdExe;
    private final UserPageQryExe userPageQryExe;
    private final UserEditLockedCmdExe userEditLockedCmdExe;
    private final UserEditPasswordCmdExe userEditPasswordCmdExe;
    private final UserProfileEditCmdExe userProfileEditCmdExe;
    private final UserProfileEditPasswordCmdExe userProfileEditPasswordCmdExe;
    private final UserProfileInfoQryExe userProfileInfoQryExe;

    @Override
    public Long add(UserAddCmd cmd) {
        return userAddCmdExe.execute(cmd);
    }

    @Override
    public UserCO getDetail(Long id) {
        return userByIdQryExe.execute(id);
    }

    @Override
    public void remove(Long id) {
        userRemoveCmdExe.execute(id);
    }

    @Override
    public void edit(UserEditCmd cmd) {
        userEditCmdExe.execute(cmd);
    }

    @Override
    public PageResult<UserCO> getPage(UserPageQry qry) {
        return userPageQryExe.execute(qry);
    }

    @Override
    public void editLocked(UserEditLockedCmd cmd) {
        userEditLockedCmdExe.execute(cmd);
    }

    @Override
    public void editPassword(UserEditPasswordCmd cmd) {
        userEditPasswordCmdExe.execute(cmd);
    }

    @Override
    public void editUserProfile(Long id, UserProfileEditCmd cmd) {
        userProfileEditCmdExe.execute(id, cmd);
    }

    @Override
    public void editUserPassword(Long loginUserId, String oldPassword, String newPassword) {
        userProfileEditPasswordCmdExe.execute(loginUserId, oldPassword, newPassword);
    }

    @Override
    public UserProfileDTO getUserProfileInfo(Long loginUserId) {
        return userProfileInfoQryExe.execute(loginUserId);
    }

}
