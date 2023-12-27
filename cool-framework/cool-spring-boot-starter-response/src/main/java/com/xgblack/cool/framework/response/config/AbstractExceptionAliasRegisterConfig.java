package com.xgblack.cool.framework.response.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
public abstract class AbstractExceptionAliasRegisterConfig implements ApplicationContextAware {

    protected abstract void registerAlias(ExceptionAliasRegister register);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

        try {
            ExceptionAliasRegister aliasRegister = applicationContext.getBean(ExceptionAliasRegister.class);
            this.registerAlias(aliasRegister);
        } catch (Exception e) {
            log.warn("未从ApplicationContext中获取到ExceptionAliasRegister实例， @ExceptionAliasFor注解将无效", e);
        }
    }


}
