package com.xgblack.cool.framework.common.response;

/**
 * @author <a href="mailto:qinyujie@gingo.cn">Yujie</a>
 */
public interface Response {

    /**
     * 设置响应行
     *
     */
    void setStatus(ResponseStatus statusLine);

    /**
     * 获取响应行
     *
     * @return
     */
    ResponseStatus getStatus();

    /**
     * 设置响应数据.
     *
     * @param payload 设置的响应数据.
     */
    void setPayload(Object payload);

    /**
     * 获得响应数据.
     *
     * @return
     */
    Object getPayload();
}
