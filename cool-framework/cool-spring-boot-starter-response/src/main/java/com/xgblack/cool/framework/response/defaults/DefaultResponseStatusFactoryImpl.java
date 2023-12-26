package com.xgblack.cool.framework.response.defaults;


import com.xgblack.cool.framework.response.GracefulResponseProperties;
import com.xgblack.cool.framework.response.api.ResponseStatusFactory;
import com.xgblack.cool.framework.response.data.ResponseStatus;
import jakarta.annotation.Resource;

/**
 * 提供的默认的ResponseMetaFactory实现.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
public class DefaultResponseStatusFactoryImpl implements ResponseStatusFactory {

    @Resource
    private GracefulResponseProperties properties;

    @Override
    public ResponseStatus defaultSuccess() {

        DefaultResponseStatus defaultResponseStatus = new DefaultResponseStatus();
        defaultResponseStatus.setCode(properties.getDefaultSuccessCode());
        defaultResponseStatus.setMsg(properties.getDefaultSuccessMsg());
        return defaultResponseStatus;
    }

    @Override
    public ResponseStatus defaultError() {
        DefaultResponseStatus defaultResponseStatus = new DefaultResponseStatus();
        defaultResponseStatus.setCode(properties.getDefaultErrorCode());
        defaultResponseStatus.setMsg(properties.getDefaultErrorMsg());
        return defaultResponseStatus;
    }

    @Override
    public ResponseStatus newInstance(String code, String msg) {
        return new DefaultResponseStatus(code, msg);
    }
}
