package com.xgblack.cool.framework.mybatis.config;

import com.mybatisflex.core.FlexGlobalConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * @author xg BLACK
 * @date 2023/12/24 15:41
 */
@AutoConfiguration
//@MapperScan(value = "${cc.info.base-package}", annotationClass = Mapper.class)
public class CoolMybatisAutoConfiguration {

    public void config() {
        //设置逻辑删除默认值
        FlexGlobalConfig globalConfig = FlexGlobalConfig.getDefaultConfig();
        //设置数据库正常时的值
        globalConfig.setNormalValueOfLogicDelete(false);
        //设置数据已被删除时的值
        globalConfig.setDeletedValueOfLogicDelete(true);
    }

}
