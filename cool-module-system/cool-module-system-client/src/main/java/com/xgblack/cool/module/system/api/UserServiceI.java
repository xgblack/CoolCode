package com.xgblack.cool.module.system.api;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.dto.user.*;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface UserServiceI {

    void add(UserAddCmd cmd);

    UserCO getDetail(Long id);

    void remove(Long id);

    void edit(UserEditCmd cmd);

    PageResult<UserCO> getPage(UserPageQry qry);

    void editLocked(UserEditLockedCmd cmd);

    void editPassword(UserEditPasswordCmd cmd);

    void editUserProfile(Long id, UserProfileEditCmd cmd);

    void editUserPassword(Long loginUserId, String oldPassword, String newPassword);
}
