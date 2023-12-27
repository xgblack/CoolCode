package com.xgblack.cool.framework.response.advice;


import com.xgblack.cool.framework.common.constants.DefaultResponseConstants;
import com.xgblack.cool.framework.common.response.Response;
import com.xgblack.cool.framework.common.response.ResponseStatus;
import com.xgblack.cool.framework.common.response.api.ResponseFactory;
import com.xgblack.cool.framework.common.response.api.ResponseStatusFactory;
import com.xgblack.cool.framework.response.CoolResponseProperties;
import com.xgblack.cool.framework.response.api.ValidationStatusCode;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
//@ControllerAdvice
//@Order(100)
public class ValidationExceptionAdvice {

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Resource
    private ResponseStatusFactory responseStatusFactory;

    @Resource
    private ResponseFactory responseFactory;

    @Resource
    private CoolResponseProperties coolResponseProperties;

    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public Response exceptionHandler(Exception e) throws Exception {

        if (e instanceof MethodArgumentNotValidException || e instanceof BindException) {
            ResponseStatus responseStatus = this.handleBindException((BindException) e);
            return responseFactory.newInstance(responseStatus);
        }

        if (e instanceof ConstraintViolationException) {
            ResponseStatus responseStatus = this.handleConstraintViolationException(e);
            return responseFactory.newInstance(responseStatus);
        }

        return responseFactory.newFailInstance();
    }

    //Controller方法的参数校验码
    //Controller方法>Controller类>DTO入参类>配置文件默认参数码>默认错误码
    private ResponseStatus handleBindException(BindException e) throws Exception {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String msg = allErrors.stream().map(error -> {
            if (error instanceof FieldError fieldError) {
                return fieldError.getField() + ":" + fieldError.getDefaultMessage();
            }
            return error.getDefaultMessage();
        }).collect(Collectors.joining(";"));
        Long code;
        //Controller方法上的注解
        ValidationStatusCode validateStatusCode = this.findValidationStatusCodeInController();
        if (validateStatusCode != null) {
            code = validateStatusCode.code();
            return responseStatusFactory.newInstance(code, msg);
        }
        List<FieldError> fieldErrors = e.getFieldErrors();
        if (!CollectionUtils.isEmpty(fieldErrors)) {
            Object target = e.getTarget();
            Class<?> clazz = target.getClass();
            //类上面找到注解
            ValidationStatusCode annotation = clazz.getAnnotation(ValidationStatusCode.class);
            //判断DTO类上是否定义
            if (annotation != null) {
                code = annotation.code();
                return responseStatusFactory.newInstance(code, msg);
            }
        }
        //默认的参数异常码 / 默认的异常码
        code = coolResponseProperties.getDefaultValidateErrorCode();
        return responseStatusFactory.newInstance(Objects.requireNonNullElse(code, DefaultResponseConstants.DEFAULT_ERROR_CODE), msg);
    }

    /**
     * 当前Controller方法
     *
     * @return
     * @throws Exception
     */
    private Method currentControllerMethod() throws Exception {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        HandlerExecutionChain handlerChain = requestMappingHandlerMapping.getHandler(sra.getRequest());
        assert handlerChain != null;
        HandlerMethod handler = (HandlerMethod) handlerChain.getHandler();
        return handler.getMethod();
    }

    private ResponseStatus handleConstraintViolationException(Exception e) throws Exception {

        ConstraintViolationException exception = (ConstraintViolationException) e;
        Set<ConstraintViolation<?>> violationSet = exception.getConstraintViolations();
        //TODO ：格式可优化
        String msg = violationSet.stream().map(s -> s.getConstraintDescriptor().getMessageTemplate()).collect(Collectors.joining(";"));
        Long code;
        ValidationStatusCode validationStatusCode = this.findValidationStatusCodeInController();
        if (validationStatusCode != null) {
            code = validationStatusCode.code();
            return responseStatusFactory.newInstance(code, msg);
        }
        //默认的参数异常码
        code = coolResponseProperties.getDefaultValidateErrorCode();
        return responseStatusFactory.newInstance(Objects.requireNonNullElse(code, DefaultResponseConstants.DEFAULT_ERROR_CODE), msg);
    }

    /**
     * 找Controller中的ValidationStatusCode注解
     * 当前方法->当前Controller类
     *
     * @return
     * @throws Exception
     */
    private ValidationStatusCode findValidationStatusCodeInController() throws Exception {
        Method method = this.currentControllerMethod();
        //Controller方法上的注解
        ValidationStatusCode validateStatusCode = method.getAnnotation(ValidationStatusCode.class);
        //Controller类上的注解
        if (validateStatusCode == null) {
            validateStatusCode = method.getDeclaringClass().getAnnotation(ValidationStatusCode.class);
        }
        return validateStatusCode;
    }
}
