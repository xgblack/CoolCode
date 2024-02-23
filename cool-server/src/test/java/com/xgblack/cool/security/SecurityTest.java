package com.xgblack.cool.security;

import org.dromara.hutool.crypto.asymmetric.RSA;
import com.xgblack.cool.framework.security.utils.PasswdUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
public class SecurityTest {

    @Test
    public void testEncryptPassword(){
        String password = PasswdUtils.encryptPassword("secret");
        log.info("password = {}", password);
    }

    @Test
    public void testAes(){
        //生成公钥和私钥
        RSA rsa = new RSA();
        log.info("私钥={}", rsa.getPrivateKeyBase64());
        log.info("公钥={}", rsa.getPublicKeyBase64());
    }

    @Test
    public void testRsa(){
        String pEncode = PasswdUtils.encode("secret");
        log.info("pEncode={}", pEncode);
        String pDecode = PasswdUtils.decode(pEncode);
        log.info("pDecode={}", pDecode);
        String encryptedPassword = PasswdUtils.encryptPassword(pDecode);
        log.info("encryptedPassword={}", encryptedPassword);
        log.info("matches={}", PasswdUtils.matches("secret", encryptedPassword));
    }
}
