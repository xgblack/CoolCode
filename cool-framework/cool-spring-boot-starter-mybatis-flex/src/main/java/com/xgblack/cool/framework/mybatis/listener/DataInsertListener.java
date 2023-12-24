package com.xgblack.cool.framework.mybatis.listener;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.mybatisflex.annotation.InsertListener;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xg BLACK
 * @date 2023/11/12 17:36
 */
@Slf4j
public class DataInsertListener implements InsertListener {
    @Override
    public void onInsert(Object entity) {
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("createTime") || field.getName().equals("updateTime")) {
                if (field.getType() == LocalDateTime.class) {
                    field.setAccessible(true);
                    try {
                        field.set(entity, LocalDateTime.now());
                    } catch (IllegalAccessException e) {
                        log.error("onInsert error. exception cause by : {}", ExceptionUtil.stacktraceToOneLineString(e));
                    }
                } else if (field.getType() == Date.class) {
                    try {
                        field.set(entity, new Date());
                    } catch (IllegalAccessException e) {
                        log.error("onInsert error. exception cause by : {}", ExceptionUtil.stacktraceToOneLineString(e));
                    }
                }

            }
        }
    }
}
