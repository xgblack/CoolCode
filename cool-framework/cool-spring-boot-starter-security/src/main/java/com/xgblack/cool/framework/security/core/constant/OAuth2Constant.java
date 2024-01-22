package com.xgblack.cool.framework.security.core.constant;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Deprecated
public class OAuth2Constant {

    /**
     * 密码模式（自定义）
     */
    public static final String GRANT_TYPE_PASSWORD = "authorization_password";

    /**
     * 短信验证码模式（自定义）
     */
    public static final String GRANT_TYPE_MOBILE = "authorization_mobile";

    /**
     * 短信验证码
     */
    public static final String SMS_CODE = "sms_code";

    /**
     * 短信验证码默认值
     */
    public static final String SMS_CODE_VALUE = "8888";

    /**
     * 登录地址
     */
    public static final String LOGIN_URL = "/login";


    /**
     * 构造方法私有化
     */
    private OAuth2Constant(){

    }

}
