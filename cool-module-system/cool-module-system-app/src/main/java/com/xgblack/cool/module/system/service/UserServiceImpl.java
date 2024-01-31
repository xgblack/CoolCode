package com.xgblack.cool.module.system.service;

import com.xgblack.cool.framework.common.pojo.PageResult;
import com.xgblack.cool.module.system.api.UserServiceI;
import com.xgblack.cool.module.system.dto.user.UserAddCmd;
import com.xgblack.cool.module.system.dto.user.UserEditCmd;
import com.xgblack.cool.module.system.dto.user.UserPageQry;
import com.xgblack.cool.module.system.dto.user.clientobject.UserCO;
import com.xgblack.cool.module.system.executor.UserAddCmdExe;
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

    @Override
    public void save(UserAddCmd cmd) {
        userAddCmdExe.execute(cmd);
    }

    @Override
    public UserCO getDetail(Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(UserEditCmd cmd) {

    }

    @Override
    public PageResult<UserCO> getPage(UserPageQry qry) {
        return null;
    }
}
