package com.xgblack.cool.module.system.common.enums.permission;

import com.xgblack.cool.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 数据范围枚举类
 * <p>用于实现数据级别的权限
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum implements IntArrayValuable {

    /** 全部数据权限 */
    ALL(1), //

    /** 指定部门数据权限 */
    DEPT_CUSTOM(2),
    /** 部门数据权限 */
    DEPT_ONLY(3),
    /** 部门及以下数据权限 */
    DEPT_AND_CHILD(4),

    /** 仅本人数据权限 */
    SELF(5),

    ;

    /**
     * 范围
     */
    private final Integer scope;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(DataScopeEnum::getScope).toArray();

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
