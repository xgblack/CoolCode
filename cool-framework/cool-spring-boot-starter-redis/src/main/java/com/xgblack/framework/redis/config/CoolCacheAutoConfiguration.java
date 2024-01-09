package com.xgblack.framework.redis.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring Cache 自动配置类
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@EnableCaching
@AutoConfiguration
//@AutoConfigureBefore(RedisAutoConfiguration.class)
@EnableConfigurationProperties({CacheProperties.class})
public class CoolCacheAutoConfiguration {

}
