package com.xgblack.cool.framework.security.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.io.resource.ResourceUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.crypto.asymmetric.KeyType;
import org.dromara.hutool.crypto.asymmetric.RSA;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码 工具类
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
@UtilityClass
public class PasswdUtils {

    /**
     * 密码加密使用 BCryptPasswordEncoder
     */
    private final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final static String privateKeyPath = "rsa/private.key";
    private final static String publicKeyPath = "rsa/public.key";

    private final static String privateKey;
    private final static String publicKey;

    private final static RSA rsa;

    static{
        privateKey = ResourceUtil.readUtf8Str(privateKeyPath);
        publicKey = ResourceUtil.readUtf8Str(publicKeyPath);

        if (StrUtil.isBlank(privateKey) || StrUtil.isBlank(publicKey)) {
            log.error("RSA公钥和密钥文件为空，将使用随机密钥对");
            //无密钥文件时使用随机（不建议，因为密钥对需要与前端使用的对应）
            rsa = new RSA();
        } else {
            rsa = new RSA(privateKey, publicKey);
        }
    }

    /**
     * 密码加密
     * @param password 明文密码
     * @return 密文密码 用于数据库存储
     */
    public static String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * 校验密码
     * @param rawPassword 当前密码(明文)
     * @param encodedPassword 数据库存储的密码
     * @return 校验结果
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 密码解密
     *
     * @param presentedPassword 加密密码
     */
    public String decode(String presentedPassword) {
        //私钥解密
        return rsa.decryptStr(presentedPassword, KeyType.PrivateKey);
    }

    /**
     * 明文密码编码
     * @param password 明文密码
     * @return 编码后的密码  用于前端加密传输给后端
     */
    public static String encode(String password) {
        return rsa.encryptBase64(password, KeyType.PublicKey);
    }

    /**
     * 将RSA加密的密码解密并加密为BC加密的密码
     * @param presentedPassword 加密密码
     * @return 密文密码（数据库）
     */
    public static String decodeAndEncryptPassword(String presentedPassword) {
        //TODO: 查看是否需要URL解码
        //return encryptPassword(decode(URLUtil.decode(presentedPassword)));
        return encryptPassword(decode(presentedPassword));
    }
}
