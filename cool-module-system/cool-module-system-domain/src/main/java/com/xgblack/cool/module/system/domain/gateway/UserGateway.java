package com.xgblack.cool.module.system.domain.gateway;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.domain.user.User;
import com.xgblack.cool.module.system.dto.user.UserEditLockedCmd;
import com.xgblack.cool.module.system.dto.user.UserPageQry;
import com.xgblack.cool.module.system.dto.user.UserProfileEditCmd;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface UserGateway {

    Long create(User user);

    User getById(Long id);

    void update(User user);

    void delete(Long id);

    PageResult<User> getPage(UserPageQry qry);

    void updateUserLocked(UserEditLockedCmd cmd);

    void updatePassword(Long id, String password);

    void updateUserProfile(Long id, UserProfileEditCmd cmd);
}
