package com.xgblack.cool.framework.mybatis.type;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.mybatisflex.core.handler.BaseJsonTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.HashSet;
import java.util.Set;

/**
 * Set<Long>与json字符串转换
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Set.class)
public class LongSetJsonTypeHandler extends BaseJsonTypeHandler<Set<Long>> {
    @Override
    protected Set<Long> parseJson(String json) {
        if (StrUtil.isBlank(json)) {
            return null;
        }
        return new HashSet<>(JSONUtil.toList(json, Long.class));
    }

    @Override
    protected String toJson(Set<Long> object) {
        return JSONUtil.toJsonStr(object);
    }
}
