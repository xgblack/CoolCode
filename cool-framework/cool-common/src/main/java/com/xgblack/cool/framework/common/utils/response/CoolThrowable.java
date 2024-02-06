package com.xgblack.cool.framework.common.utils.response;


import com.xgblack.cool.framework.common.constants.DefaultResponseConstants;
import com.xgblack.cool.framework.common.exception.BaseException;
import com.xgblack.cool.framework.common.response.api.AssertFunction;
import org.dromara.hutool.core.lang.Assert;

import java.util.Collection;

/**
 * 工具类
 */
public class CoolThrowable {

    /**
     * 需要抛自定义异常时，调用该方法
     * @param msg 异常提示
     */
    public static void raiseException(String msg) {
        throw new BaseException(DefaultResponseConstants.DEFAULT_ERROR_CODE, msg);
    }

    /**
     * 需要抛自定义异常时，调用该方法
     *
     * @param code 异常码
     * @param msg  异常提示
     */
    public static void raiseException(long code, String msg) {
        throw new BaseException(code, msg);
    }

    /**
     * 需要抛自定义异常时，调用该方法
     *
     * @param code      异常码
     * @param msg       异常提示
     * @param throwable 捕获的异常
     */
    public static void raiseException(long code, String msg, Throwable throwable) {
        throw new BaseException(code, msg, throwable);
    }

    /**
     * 断言异常封装
     * @param assertFunction 断言
     */
    public static void wrapAssert(AssertFunction assertFunction) {
        try {
            assertFunction.doAssert();
        } catch (Exception e) {
            throw new BaseException(e.getMessage(), e);
        }
    }

    /**
     * 断言异常封装
     * @param code 自定义异常码
     * @param assertFunction 断言
     */
    public static void wrapAssert(long code, AssertFunction assertFunction) {
        try {
            assertFunction.doAssert();
        } catch (Exception e) {
            throw new BaseException(code, e.getMessage(), e);
        }
    }

    /**
     * 断言工具 判断数据是否存在
     * @param data 数据对象
     */
    public static void dataFoundAssert(Object data) {
        wrapAssert(() -> Assert.notNull(data,"数据不存在"));
    }

    /**
     * 断言工具 判断数据是否存在
     * @param collection 数据集合
     */
    public static void dataFoundAssert(Collection<?> collection) {
        wrapAssert(() -> Assert.notEmpty(collection,"数据不存在"));
    }


    /**
     * 断言工具 判断数据是否删除成功
     * @param expression 数据操作标识
     */
    public static void dataDeleteAssert(boolean expression) {
        wrapAssert(() -> Assert.isTrue(expression,"数据删除失败"));
    }

    /**
     * 断言工具 判断数据是否保存成功
     * @param expression 数据操作标识
     */
    public static void dataSaveAssert(boolean expression) {
        wrapAssert(() -> Assert.isTrue(expression,"数据保存失败"));
    }

    /**
     * 断言工具 判断数据是否修改成功
     * @param expression 数据操作标识
     */
    public static void dataUpdateAssert(boolean expression) {
        wrapAssert(() -> Assert.isTrue(expression,"数据修改失败"));
    }
}
