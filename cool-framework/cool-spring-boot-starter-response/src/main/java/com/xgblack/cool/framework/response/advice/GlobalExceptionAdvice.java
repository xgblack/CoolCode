package com.xgblack.cool.framework.response.advice;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.xgblack.cool.framework.common.annotation.response.ExceptionAliasFor;
import com.xgblack.cool.framework.common.annotation.response.ExceptionMapper;
import com.xgblack.cool.framework.common.constants.DefaultResponseConstants;
import com.xgblack.cool.framework.common.exception.BaseException;
import com.xgblack.cool.framework.common.response.Response;
import com.xgblack.cool.framework.common.response.ResponseStatus;
import com.xgblack.cool.framework.common.response.api.ResponseFactory;
import com.xgblack.cool.framework.common.response.api.ResponseStatusFactory;
import com.xgblack.cool.framework.response.config.CoolResponseProperties;
import com.xgblack.cool.framework.response.config.ExceptionAliasRegister;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理.
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
//@ControllerAdvice
//@Order(200)
public class GlobalExceptionAdvice implements ApplicationContextAware {

    @Resource
    private ResponseStatusFactory responseStatusFactory;

    @Resource
    private ResponseFactory responseFactory;

    private ExceptionAliasRegister exceptionAliasRegister;

    @Resource
    private CoolResponseProperties coolResponseProperties;

    @Resource
    private CoolResponseProperties properties;

    /**
     * 异常处理逻辑.
     *
     * @param throwable 业务逻辑抛出的异常
     * @return 统一返回包装后的结果
     */
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public Response exceptionHandler(Throwable throwable) {
        if (coolResponseProperties.isPrintExceptionInGlobalAdvice()) {
            log.error("GlobalExceptionAdvice捕获到异常, exception : {}", ExceptionUtil.stacktraceToString(throwable));
        }
        ResponseStatus statusLine;
        if (throwable instanceof BaseException) {
            statusLine = fromBaseExceptionInstance((BaseException) throwable);
        } else {
            //校验异常转自定义异常
            statusLine = fromExceptionInstance(throwable);
        }
        return responseFactory.newInstance(statusLine);
    }

    /**
     * 根据异常基类 返回响应
     * @param exception
     * @return
     */
    private ResponseStatus fromBaseExceptionInstance(BaseException exception) {
        Long code = exception.getCode();
        if (code == null) {
            code = DefaultResponseConstants.DEFAULT_ERROR_CODE;
        }
        return responseStatusFactory.newInstance(code, exception.getMsg());
    }

    /**
     * 非异常基类及其子类
     * @param throwable
     * @return
     */
    private ResponseStatus fromExceptionInstance(Throwable throwable) {

        Class<? extends Throwable> clazz = throwable.getClass();

        ExceptionMapper exceptionMapper = clazz.getAnnotation(ExceptionMapper.class);

        //1.有@ExceptionMapper注解，直接设置结果的状态
        if (exceptionMapper != null) {
            boolean msgReplaceable = exceptionMapper.msgReplaceable();
            //异常提示可替换+抛出来的异常有自定义的异常信息
            if (msgReplaceable) {
                String throwableMessage = throwable.getMessage();
                if (throwableMessage != null) {
                    return responseStatusFactory.newInstance(exceptionMapper.code(), throwableMessage);
                }
            }
            return responseStatusFactory.newInstance(exceptionMapper.code(), exceptionMapper.msg());
        }

        //2.有@ExceptionAliasFor异常别名注解，获取已注册的别名信息
        if (exceptionAliasRegister != null) {
            ExceptionAliasFor exceptionAliasFor = exceptionAliasRegister.getExceptionAliasFor(clazz);
            if (exceptionAliasFor != null) {
                return responseStatusFactory.newInstance(exceptionAliasFor.code(), exceptionAliasFor.msg());
            }
        }
        ResponseStatus defaultError = responseStatusFactory.defaultError();


        //3. 例外的异常类型 比如断言可能会抛出的IllegalArgumentException
        // 会造成其他地方抛出的此类异常也会包装后返回
        //4. 原生异常+originExceptionUsingDetailMessage=true
        //如果有自定义的异常信息，原生异常将直接使用异常信息进行返回，不再返回默认错误提示
        if (throwable instanceof IllegalArgumentException || properties.getOriginExceptionUsingDetailMessage()) {
            String throwableMessage = throwable.getMessage();
            if (throwableMessage != null) {
                defaultError.setMsg(throwableMessage);
            }
        }
        return defaultError;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.exceptionAliasRegister = applicationContext.getBean(ExceptionAliasRegister.class);
    }
}
