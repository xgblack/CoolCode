package com.xgblack.cool.framework.response.config;


import com.xgblack.cool.framework.response.api.ExceptionAliasFor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ExceptionAliasRegister {

    private ConcurrentHashMap<Class<? extends Throwable>, ExceptionAliasFor> aliasForMap = new ConcurrentHashMap<>();

    /**
     * 注册
     *
     * @param throwableClass
     * @return
     */
    public ExceptionAliasRegister doRegisterExceptionAlias(Class<? extends Throwable> throwableClass) {

        ExceptionAliasFor exceptionAliasFor = throwableClass.getAnnotation(ExceptionAliasFor.class);
        if (exceptionAliasFor == null) {
            log.error("注册了未加ExceptionAliasFor的异常,throwableClass={}", throwableClass);
            throw new RuntimeException();
        }

        Class<? extends Throwable>[] classes = exceptionAliasFor.aliasFor();
        for (Class<? extends Throwable> c : classes) {
            aliasForMap.put(c, exceptionAliasFor);
        }

        return this;
    }

    /**
     * 获取
     *
     * @param tClazz
     * @return
     */
    public ExceptionAliasFor getExceptionAliasFor(Class<? extends Throwable> tClazz) {
        return aliasForMap.get(tClazz);
    }
}
