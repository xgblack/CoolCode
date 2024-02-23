package com.xgblack.cool.other;

import com.xgblack.cool.framework.mybatis.dataobject.BaseDO;
import com.xgblack.cool.module.system.gateway.database.dataobject.StudentDO;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.reflect.FieldUtil;
import org.dromara.hutool.core.reflect.TypeUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
public class HutoolTest {

    @Test
    public void testField(){
        StudentDO entity = new StudentDO().setId(100L).setAge(20).setBirthday(LocalDateTime.now()).setDate(new Date()).setName("张三");

        Field createTime = FieldUtil.getField(entity.getClass(), BaseDO.Fields.createTime);
        Field creator = FieldUtil.getField(entity.getClass(), BaseDO.Fields.creator);

        if (createTime != null) {
            if (TypeUtil.getType(createTime) == LocalDateTime.class) {
                FieldUtil.setFieldValue(entity, createTime, LocalDateTime.now());
            }else if (TypeUtil.getType(createTime) == Date.class) {
                FieldUtil.setFieldValue(entity, createTime, new Date());
            }
        }
        if (creator != null) {
            FieldUtil.setFieldValue(entity, creator, 110L);
        }

        log.info("entity = {}", entity);
    }
}
