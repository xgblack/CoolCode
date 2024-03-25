package com.xgblack.cool.framework.operatelog.config;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@AutoConfiguration
@EnableLogRecord(tenant = "com.xgblack.cool")//TODO 分辨此处租户的意义
public class CoolOperateLogAutoConfiguration {

}
