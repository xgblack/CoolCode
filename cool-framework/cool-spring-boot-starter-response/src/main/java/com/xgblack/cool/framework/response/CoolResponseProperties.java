package com.xgblack.cool.framework.response;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 核心配置类.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "cc.response")
public class CoolResponseProperties {

    /**
     * 在全局异常处理器中打印异常，默认不打印
     */
    private boolean printExceptionInGlobalAdvice = false;

    /**
     * 例外包路径
     */
    private List<String> excludePackages;

    /**
     * 不使用@ExceptionMapper和@ExceptionAliasFor修饰的原生异常
     * 是否使用异常信息Throwable类的detailMessage进行返回
     * originExceptionUsingDetailMessage=false，则msg=defaultErrorMsg
     */
    private Boolean originExceptionUsingDetailMessage = true;

    /**
     * 默认的Response实现类名称，配置了responseClassFullName，则responseStyle不生效
     */
    //private String responseClassFullName = "com.xgblack.cool.framework.response.defaults.DefaultResponse";

    /**
     * 默认的成功返回码
     */
    //private String defaultSuccessCode = String.valueOf(DefaultConstants.DEFAULT_SUCCESS_CODE);

    /**
     * 默认的成功提示
     */
    //private String defaultSuccessMsg = DefaultConstants.DEFAULT_SUCCESS_MSG;

    /**
     * 默认的失败码
     */
    //private String defaultErrorCode = String.valueOf(DefaultConstants.DEFAULT_ERROR_CODE);

    /**
     * 默认的失败提示
     */
    //private String defaultErrorMsg = DefaultConstants.DEFAULT_ERROR_MSG;

    /**
     * Validate异常码，不提供的话默认DefaultConstants.DEFAULT_ERROR_CODE
     */
    private Long defaultValidateErrorCode;


}
