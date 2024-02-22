package com.xgblack.cool.framework.mybatis.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MybatisFlex配置
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "cc.sql")
public class MybatisFlexProperties {
    /**
     * 是否开启SQL审计
     */
    private Boolean auditEnable = false;

}
