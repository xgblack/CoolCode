package com.xgblack.cool.framework.mybatis.type;

import org.dromara.hutool.json.JSONUtil;
import com.mybatisflex.core.handler.BaseJsonTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

/**
 * List<Price> 与 json字符串 互转
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class StringListJsonTypeHandler extends BaseJsonTypeHandler<List<String>> {
    @Override
    protected List<String> parseJson(String json) {
        return JSONUtil.toList(json, String.class);
    }

    @Override
    protected String toJson(List<String> object) {
        return JSONUtil.toJsonStr(object);
    }
}
