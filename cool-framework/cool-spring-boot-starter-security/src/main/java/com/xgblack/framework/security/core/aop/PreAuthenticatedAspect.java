package com.xgblack.framework.security.core.aop;

import lombok.extern.slf4j.Slf4j;

@Deprecated
//@Aspect
@Slf4j
public class PreAuthenticatedAspect {

    //@Around("@annotation(preAuthenticated)")
    /*public Object around(ProceedingJoinPoint joinPoint, PreAuthenticated preAuthenticated) throws Throwable {
        if (SecurityUtils.getUser() == null) {
            throw new UnauthorizedException();
        }
        return joinPoint.proceed();
    }*/

}
