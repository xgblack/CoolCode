package com.xgblack.cool.module.system.api;

import com.xgblack.cool.framework.common.pojo.PageResult;
import com.xgblack.cool.module.system.dto.user.UserAddCmd;
import com.xgblack.cool.module.system.dto.user.UserEditCmd;
import com.xgblack.cool.module.system.dto.user.UserPageQry;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface UserServiceI {

    void save(UserAddCmd cmd);

    UserCO getDetail(Long id);

    void remove(Long id);

    void update(UserEditCmd cmd);

    PageResult<UserCO> getPage(UserPageQry qry);

}
