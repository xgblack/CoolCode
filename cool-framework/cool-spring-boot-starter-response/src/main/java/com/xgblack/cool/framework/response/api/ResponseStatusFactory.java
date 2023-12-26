package com.xgblack.cool.framework.response.api;


import com.xgblack.cool.framework.response.data.ResponseStatus;

/**
 * @author <a href="mailto:qinyujie@gingo.cn">Yujie</a>
 */
public interface ResponseStatusFactory {
    /**
     * 获得响应成功的ResponseMeta.
     *
     * @return
     */
    ResponseStatus defaultSuccess();

    /**
     * 获得失败的ResponseMeta.
     *
     * @return
     */
    ResponseStatus defaultError();


    /**
     * 从code和msg创建ResponseStatus
     * @param code
     * @param msg
     * @return
     */
    ResponseStatus newInstance(String code,String msg);

}
