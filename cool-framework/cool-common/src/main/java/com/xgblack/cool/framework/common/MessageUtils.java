package com.xgblack.cool.framework.common;

import cn.hutool.core.util.StrUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取i18n资源文件
 *
 * @author ruoyi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageUtils {

    public static MessageSource messageSource;

    public static String message(String code, Object... args) {
        if (messageSource != null) {
            return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
        } else {
            return code;
        }
    }

    public static String message(String code, Object[] args, String defaultMsg) {
        if (messageSource != null) {
            return messageSource.getMessage(code, args, defaultMsg, LocaleContextHolder.getLocale());
        } else {
            return StrUtil.isNotBlank(defaultMsg) ? defaultMsg : code;
        }
    }

}
