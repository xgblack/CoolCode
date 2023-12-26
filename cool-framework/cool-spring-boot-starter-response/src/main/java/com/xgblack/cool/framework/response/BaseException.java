package com.xgblack.cool.framework.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 异常基类
 */
@Getter
@NoArgsConstructor
public class BaseException extends RuntimeException {

    /**
     * 响应码
     */
    private Long code;
    /**
     * 提示信息
     */
    private String msg;


    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(Long code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public BaseException(Long code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

}
