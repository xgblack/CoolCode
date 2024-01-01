package com.xgblack.framework.security.core.aop;

import com.xgblack.cool.framework.common.exception.security.UnauthorizedException;
import com.xgblack.framework.security.core.annotation.PreAuthenticated;
import com.xgblack.framework.security.core.utils.SecurityFrameworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
@Slf4j
public class PreAuthenticatedAspect {

    @Around("@annotation(preAuthenticated)")
    public Object around(ProceedingJoinPoint joinPoint, PreAuthenticated preAuthenticated) throws Throwable {
        if (SecurityFrameworkUtils.getLoginUser() == null) {
            throw new UnauthorizedException();
        }
        return joinPoint.proceed();
    }

}
