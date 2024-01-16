package com.xgblack.framework.security.core.utils;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * OAuthClientException 异常信息
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class OAuthClientException extends OAuth2AuthenticationException {

    /**
     * Constructs a <code>ScopeException</code> with the specified message.
     * @param msg the detail message.
     */
    public OAuthClientException(String msg) {
        super(new OAuth2Error(msg), msg);
    }

    /**
     * Constructs a {@code ScopeException} with the specified message and root cause.
     * @param msg the detail message.
     * @param cause root cause
     */
    public OAuthClientException(String msg, Throwable cause) {
        super(new OAuth2Error(msg), cause);
    }
}
