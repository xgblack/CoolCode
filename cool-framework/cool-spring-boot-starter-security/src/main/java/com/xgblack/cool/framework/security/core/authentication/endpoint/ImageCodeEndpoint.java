package com.xgblack.cool.framework.security.core.authentication.endpoint;

import com.xgblack.cool.framework.common.constants.CacheConstants;
import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.cool.framework.security.annotation.Inner;
import io.springboot.captcha.ArithmeticCaptcha;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 验证码相关的接口 TODO：优化
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Inner(false)
@RestController
@RequestMapping("/code")
@RequiredArgsConstructor
public class ImageCodeEndpoint {

    private static final Integer DEFAULT_IMAGE_WIDTH = 100;

    private static final Integer DEFAULT_IMAGE_HEIGHT = 40;

    private final RedisTemplate redisTemplate;

    /**
     * 创建图形验证码
     */
    @SneakyThrows
    @GetMapping("/image")
    public void image(String randomStr, HttpServletResponse response) {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);

        String result = captcha.text();
        redisTemplate.opsForValue().set(CacheConstants.DEFAULT_CODE_KEY + randomStr, result, SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
        // 转换流信息写出
        captcha.out(response.getOutputStream());
    }

}
