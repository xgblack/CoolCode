package com.xgblack.cool.framework.response.exception;

import com.xgblack.cool.framework.common.annotation.response.ExceptionAliasFor;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@ExceptionAliasFor(code = 404, msg = "Not Found", aliasFor = {NoHandlerFoundException.class, NoResourceFoundException.class})
public class NotFoundException  extends RuntimeException{
}
