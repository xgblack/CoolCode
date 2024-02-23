package com.xgblack.cool.framework.datasource.config;


import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据库配置类
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@AutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true) // 启动事务管理
public class CoolDataSourceAutoConfiguration {



}
