package com.xgblack.cool.framework.response.utils;


import com.xgblack.cool.framework.response.BaseException;
import com.xgblack.cool.framework.response.api.AssertFunction;
import com.xgblack.cool.framework.response.defaults.DefaultConstants;

/**
 * 工具类
 */
public class ExceptionUtils {

    /**
     * 需要抛自定义异常时，调用该方法
     * @param msg 异常提示
     */
    public static void raiseException(String msg) {
        throw new BaseException(DefaultConstants.DEFAULT_ERROR_CODE, msg);
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
}
