package com.xgblack.cool.module.system.service;

import com.xgblack.cool.framework.common.pojo.PageResult;
import com.xgblack.cool.module.system.api.UserServiceI;
import com.xgblack.cool.module.system.dto.user.UserAddCmd;
import com.xgblack.cool.module.system.dto.user.UserEditCmd;
import com.xgblack.cool.module.system.dto.user.UserEditLockedCmd;
import com.xgblack.cool.module.system.dto.user.UserPageQry;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;
import com.xgblack.cool.module.system.executor.UserAddCmdExe;
import com.xgblack.cool.module.system.executor.UserEditCmdExe;
import com.xgblack.cool.module.system.executor.UserEditLockedCmdExe;
import com.xgblack.cool.module.system.executor.UserRemoveCmdExe;
import com.xgblack.cool.module.system.executor.query.UserByIdQryExe;
import com.xgblack.cool.module.system.executor.query.UserPageQryExe;
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

    @Override
    public void save(UserAddCmd cmd) {
        userAddCmdExe.execute(cmd);
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
    public void update(UserEditCmd cmd) {
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
}
