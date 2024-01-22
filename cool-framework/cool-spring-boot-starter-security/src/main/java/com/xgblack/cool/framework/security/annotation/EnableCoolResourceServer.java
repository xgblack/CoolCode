package com.xgblack.cool.framework.security.annotation;

import com.xgblack.cool.framework.security.config.CoolResourceServerAutoConfiguration;
import com.xgblack.cool.framework.security.config.CoolResourceServerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 资源服务注解
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import({ CoolResourceServerAutoConfiguration.class, CoolResourceServerConfiguration.class })
public @interface EnableCoolResourceServer {
}
