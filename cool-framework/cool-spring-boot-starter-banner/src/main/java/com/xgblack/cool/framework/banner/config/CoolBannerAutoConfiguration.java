package com.xgblack.cool.framework.banner.config;


import com.xgblack.cool.framework.banner.core.BannerApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Banner 的自动配置类
 * <p>
 * <a href="https://www.bootschool.net/ascii">ascii生成</a>
 * @author xg black
 */
@Configuration
public class CoolBannerAutoConfiguration {

    @Bean
    public BannerApplicationRunner bannerApplicationRunner() {
        return new BannerApplicationRunner();
    }

}
