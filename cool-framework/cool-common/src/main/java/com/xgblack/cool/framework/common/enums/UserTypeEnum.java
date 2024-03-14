package com.xgblack.cool.framework.common.enums;

import com.xgblack.cool.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dromara.hutool.core.array.ArrayUtil;

import java.util.Arrays;

/**
 * 全局用户类型枚举
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@AllArgsConstructor
@Getter
public enum UserTypeEnum  implements IntArrayValuable {
    MEMBER(1, "会员"), // 面向 c 端，普通用户
    ADMIN(2, "管理员"); // 面向 b 端，管理后台

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(UserTypeEnum::getValue).toArray();

    /**
     * 类型
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    public static UserTypeEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(userType -> userType.getValue().equals(value), UserTypeEnum.values());
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}