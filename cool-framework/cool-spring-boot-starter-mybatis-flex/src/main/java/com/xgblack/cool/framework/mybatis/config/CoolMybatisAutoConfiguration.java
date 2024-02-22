package com.xgblack.cool.framework.mybatis.config;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import com.xgblack.cool.framework.mybatis.dataobject.BaseDO;
import com.xgblack.cool.framework.mybatis.listener.DataInsertListener;
import com.xgblack.cool.framework.mybatis.listener.DataUpdateListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * MybatisFlex自动配置类
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(MybatisFlexProperties.class)
@RequiredArgsConstructor
//@MapperScan(value = "${cc.info.base-package}", annotationClass = Mapper.class)
public class CoolMybatisAutoConfiguration implements MyBatisFlexCustomizer {

    private final MybatisFlexProperties mybatisFlexProperties;

    @Override
    public void customize(FlexGlobalConfig globalConfig) {
        //一些列的初始化配置

        //设置数据库正常时的值
        globalConfig.setNormalValueOfLogicDelete(false);
        //设置数据已被删除时的值
        globalConfig.setDeletedValueOfLogicDelete(true);

        //开启审计功能
        AuditManager.setAuditEnable(mybatisFlexProperties.getAuditEnable());
        //设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage ->
                log.debug("SQL audit [{}ms] :  ==>  {}   ", auditMessage.getElapsedTime(), auditMessage.getFullSql())
        );

        //注册数据全局注入
        globalConfig.registerInsertListener(new DataInsertListener(), BaseDO.class);//TODO: 考虑是否增加租户自动注入
        globalConfig.registerUpdateListener(new DataUpdateListener(), BaseDO.class);

    }
}
