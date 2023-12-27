package com.xgblack.cool.framework.response.config;

import com.xgblack.cool.framework.response.AbstractExceptionAliasRegisterConfig;
import com.xgblack.cool.framework.response.ExceptionAliasRegister;
import com.xgblack.cool.framework.response.exception.NotFoundException;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Configuration
public class CoolResponseConfig extends AbstractExceptionAliasRegisterConfig {

    /**
     * 注册异常别名
     * @param register
     */
    @Override
    protected void registerAlias(ExceptionAliasRegister register) {
        register.doRegisterExceptionAlias(NotFoundException.class);
    }
}
