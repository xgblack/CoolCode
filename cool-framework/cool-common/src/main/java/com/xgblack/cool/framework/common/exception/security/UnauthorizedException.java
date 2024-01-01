package com.xgblack.cool.framework.common.exception.security;

import com.xgblack.cool.framework.common.annotation.response.ExceptionMapper;
import com.xgblack.cool.framework.common.constants.DefaultResponseConstants;

/**
 * 账号未登录 自定义异常
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@ExceptionMapper(code = DefaultResponseConstants.UNAUTHORIZED_ERROR_CODE, msg = "账号未登录")
public class UnauthorizedException extends RuntimeException{

}
