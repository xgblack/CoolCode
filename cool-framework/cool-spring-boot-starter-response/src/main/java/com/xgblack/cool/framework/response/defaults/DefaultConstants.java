package com.xgblack.cool.framework.response.defaults;

/**
 * 默认的响应码和提示信息
 */
public interface DefaultConstants {

    /**
     * 默认的成功响应码
     */
    long DEFAULT_SUCCESS_CODE = 0L;

    /**
     * 默认的成功提示信息
     */
    String DEFAULT_SUCCESS_MSG = "ok";

    /**
     * 默认的错误码
     */
    long DEFAULT_ERROR_CODE = -1L;

    /**
     * 默认的错误提示
     */
    String DEFAULT_ERROR_MSG = "error";

    /**
     * 默认的参数校验错误码
     */
    long DEFAULT_PARAM_ERROR_CODE = -1L;

}
