package com.xgblack.cool.framework.common.response;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
public interface ResponseStatus {
    /**
     * 设置响应码.
     *
     * @param code 设置的响应码.
     */
    void setCode(Long code);

    /**
     * 获得响应码.
     *
     */
    Long getCode();

    /**
     * 设置响应提示信息.
     *
     * @param msg 设置响应提示信息.
     */
    void setMsg(String msg);

    /**
     * 获得响应信息.
     *
     * @return
     */
    String getMsg();
}
