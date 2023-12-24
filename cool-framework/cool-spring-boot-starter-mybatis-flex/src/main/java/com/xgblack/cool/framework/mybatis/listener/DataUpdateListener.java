package com.xgblack.cool.framework.mybatis.listener;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.mybatisflex.annotation.UpdateListener;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xg BLACK
 * @date 2023/11/12 18:05
 */

@Slf4j
public class DataUpdateListener implements UpdateListener {
    @Override
    public void onUpdate(Object entity) {
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("updateTime")) {
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
