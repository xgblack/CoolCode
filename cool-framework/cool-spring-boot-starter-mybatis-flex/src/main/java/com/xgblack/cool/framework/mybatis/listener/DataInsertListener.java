package com.xgblack.cool.framework.mybatis.listener;

import com.mybatisflex.annotation.InsertListener;
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
 * 新增数据自动注入 监听
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
public class DataInsertListener implements InsertListener {
    @Override
    public void onInsert(Object entity) {

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDate nowDate = nowDateTime.toLocalDate();
        Date nowJdkTime = new Date();

        try {
            // 创建时间
            Field createTime = FieldUtil.getField(entity.getClass(), BaseDO.Fields.createTime);
            if (createTime != null) {
                if (TypeUtil.getType(createTime) == LocalDateTime.class) {
                    FieldUtil.setFieldValue(entity, createTime, nowDateTime);
                } else if (TypeUtil.getType(createTime) == Date.class) {
                    FieldUtil.setFieldValue(entity, createTime, nowJdkTime);
                } else if (TypeUtil.getType(createTime) == LocalDate.class) {
                    FieldUtil.setFieldValue(entity, createTime, nowDate);
                }
            }
        } catch (Exception e) {
            log.error("onInsert field[{}] error. exception cause by : {}", BaseDO.Fields.createTime, ExceptionUtil.stacktraceToOneLineString(e));
        }

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
            log.error("onInsert field[{}] error. exception cause by : {}", BaseDO.Fields.updateTime, ExceptionUtil.stacktraceToOneLineString(e));
        }

        try {
            // 创建者
            Field creator = FieldUtil.getField(entity.getClass(), BaseDO.Fields.creator);
            Long loginUserId = SecurityUtils.getLoginUserId();
            if (creator != null && loginUserId != null) {
                FieldUtil.setFieldValue(entity, creator, loginUserId);
            }
        } catch (Exception e) {
            log.error("onInsert field[{}] error. exception cause by : {}", BaseDO.Fields.creator, ExceptionUtil.stacktraceToOneLineString(e));
        }

        //TODO: 考虑是否增加租户自动注入

    }
}
