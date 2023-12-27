package com.xgblack.cool.framework.response.defaults;


import com.xgblack.cool.framework.common.constants.DefaultResponseConstants;
import com.xgblack.cool.framework.common.response.defaults.DefaultResponseStatus;
import com.xgblack.cool.framework.common.response.api.ResponseStatusFactory;
import com.xgblack.cool.framework.common.response.ResponseStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的ResponseMetaFactory实现.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
public class DefaultResponseStatusFactoryImpl implements ResponseStatusFactory {

    //@Resource
    //private CoolResponseProperties properties;

    @Override
    public ResponseStatus defaultSuccess() {

        DefaultResponseStatus defaultResponseStatus = new DefaultResponseStatus();
        defaultResponseStatus.setCode(DefaultResponseConstants.DEFAULT_SUCCESS_CODE);
        defaultResponseStatus.setMsg(DefaultResponseConstants.DEFAULT_SUCCESS_MSG);
        return defaultResponseStatus;
    }

    @Override
    public ResponseStatus defaultError() {
        DefaultResponseStatus defaultResponseStatus = new DefaultResponseStatus();
        defaultResponseStatus.setCode(DefaultResponseConstants.DEFAULT_ERROR_CODE);
        defaultResponseStatus.setMsg(DefaultResponseConstants.DEFAULT_ERROR_MSG);
        return defaultResponseStatus;
    }

    @Override
    public ResponseStatus newInstance(Long code, String msg) {
        return new DefaultResponseStatus(code, msg);
    }
}
