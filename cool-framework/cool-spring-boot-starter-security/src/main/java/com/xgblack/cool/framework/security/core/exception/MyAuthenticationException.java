package com.xgblack.cool.framework.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class MyAuthenticationException extends AuthenticationException {
    public MyAuthenticationException(String msg) {
        super(msg);
    }
}
