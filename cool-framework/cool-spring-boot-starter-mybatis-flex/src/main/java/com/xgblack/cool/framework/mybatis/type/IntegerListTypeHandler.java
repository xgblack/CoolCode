package com.xgblack.cool.framework.mybatis.type;

import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.convert.Convert;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.dromara.hutool.core.text.split.SplitUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * List 与 逗号分隔 Integer 转换
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class IntegerListTypeHandler implements TypeHandler<List<Integer>> {
    private static final String COMMA = ",";

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Integer> ids, JdbcType jdbcType) throws SQLException {
        // 设置占位符
        ps.setString(i, CollUtil.join(ids, COMMA));
    }

    @Override
    public List<Integer> getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return getResult(value);
    }

    @Override
    public List<Integer> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return getResult(value);
    }

    @Override
    public List<Integer> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return getResult(value);
    }

    private List<Integer> getResult(String value) {
        if (value == null) {
            return null;
        }
        List<String> strings = SplitUtil.splitTrim(value, COMMA);
        return strings.stream().map(Convert::toInt).toList();
    }
}
