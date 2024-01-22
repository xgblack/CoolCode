package com.xgblack.cool.framework.common.utils.i18n;

import com.xgblack.cool.framework.common.utils.spring.SpringUtils;
import lombok.experimental.UtilityClass;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@UtilityClass
public class MsgUtils {

    /**
     * 通过code 获取中文错误信息
     * @param code
     * @return
     */
    public String getMessage(String code) {
        MessageSource messageSource = SpringUtils.getBean("messageSource");
        return messageSource.getMessage(code, null, Locale.CHINA);
    }

    /**
     * 通过code 和参数获取中文错误信息
     * @param code
     * @return
     */
    public String getMessage(String code, Object... objects) {
        MessageSource messageSource = SpringUtils.getBean("messageSource");
        return messageSource.getMessage(code, objects, Locale.CHINA);
    }

    /**
     * security 通过code 和参数获取中文错误信息
     * @param code
     * @return
     */
    public String getSecurityMessage(String code, Object... objects) {
        MessageSource messageSource = SpringUtils.getBean("securityMessageSource");
        return messageSource.getMessage(code, objects, Locale.CHINA);
    }

}
