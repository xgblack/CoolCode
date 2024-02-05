package com.xgblack.cool.framework.mybatis.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@Setter
@Getter
@ConfigurationProperties(prefix = "cc.sql")
public class MybatisFlexProperties {
    /**
     * 是否开启SQL审计
     */
    private Boolean auditEnable = false;

}
