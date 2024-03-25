package com.xgblack.cool.framework.operatelog.core.service;

import com.mzt.logapi.beans.Operator;
import com.mzt.logapi.service.IOperatorGetService;
import com.xgblack.cool.framework.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Service
public class CoolOperatorGetServiceImpl implements IOperatorGetService {
    @Override
    public Operator getUser() {
        Long loginUserId = SecurityUtils.getLoginUserId();
        Operator operator = new Operator();
        operator.setOperatorId(Objects.nonNull(loginUserId) ? String.valueOf(loginUserId) : "0");
        return operator;
    }

}
