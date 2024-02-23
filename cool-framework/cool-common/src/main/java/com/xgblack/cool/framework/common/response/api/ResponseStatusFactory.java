package com.xgblack.cool.framework.common.response.api;


import com.xgblack.cool.framework.common.response.ResponseStatus;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
public interface ResponseStatusFactory {
    /**
     * 获得响应成功的ResponseMeta.
     *
     * @return ResponseStatus
     */
    ResponseStatus defaultSuccess();

    /**
     * 获得失败的ResponseMeta.
     *
     * @return ResponseStatus
     */
    ResponseStatus defaultError();


    /**
     * 从code和msg创建ResponseStatus
     * @param code 响应码
     * @param msg 响应信息
     * @return ResponseStatus
     */
    ResponseStatus newInstance(Long code,String msg);

}
