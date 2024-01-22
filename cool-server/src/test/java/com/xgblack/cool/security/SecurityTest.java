package com.xgblack.cool.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
public class SecurityTest {

    @Test
    public void testPassword(){
        String password = new BCryptPasswordEncoder().encode("secret");
        log.info("password = {}", password);
    }
}
