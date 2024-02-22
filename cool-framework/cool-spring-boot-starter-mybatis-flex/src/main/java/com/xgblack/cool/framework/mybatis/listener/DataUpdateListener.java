package com.xgblack.cool.framework.mybatis.listener;

import com.mybatisflex.annotation.UpdateListener;
import com.xgblack.cool.framework.mybatis.dataobject.BaseDO;
import com.xgblack.cool.framework.security.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.exception.ExceptionUtil;
import org.dromara.hutool.core.reflect.FieldUtil;
import org.dromara.hutool.core.reflect.TypeUtil;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 更新数据自动注入 监听
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
public class DataUpdateListener implements UpdateListener {
    @Override
    public void onUpdate(Object entity) {

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDate nowDate = nowDateTime.toLocalDate();
        Date nowJdkTime = new Date();

        try {
            // 更新时间
            Field updateTime = FieldUtil.getField(entity.getClass(), BaseDO.Fields.updateTime);
            if (updateTime != null) {
                if (TypeUtil.getType(updateTime) == LocalDateTime.class) {
                    FieldUtil.setFieldValue(entity, updateTime, nowDateTime);
                } else if (TypeUtil.getType(updateTime) == Date.class) {
                    FieldUtil.setFieldValue(entity, updateTime, nowJdkTime);
                } else if (TypeUtil.getType(updateTime) == LocalDate.class) {
                    FieldUtil.setFieldValue(entity, updateTime, nowDate);
                }
            }
        } catch (Exception e) {
            log.error("onUpdate field[{}] error. exception cause by : {}", BaseDO.Fields.updateTime, ExceptionUtil.stacktraceToOneLineString(e));
        }

        try {
            // 更新者
            Field updater = FieldUtil.getField(entity.getClass(), BaseDO.Fields.updater);
            Long loginUserId = SecurityUtils.getLoginUserId();
            if (updater != null && loginUserId != null) {
                FieldUtil.setFieldValue(entity, updater, loginUserId);
            }
        } catch (Exception e) {
            log.error("onUpdate field[{}] error. exception cause by : {}", BaseDO.Fields.updater, ExceptionUtil.stacktraceToOneLineString(e));
        }

    }
}
