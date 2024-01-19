package com.xgblack.cool.framework.security.core.authentication.oidc;

import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 自定义 OidcUserInfo 构造器
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public class MyOidcUserInfo extends OidcUserInfo {
    private static final long serialVersionUID = 610L;
    private final Map<String, Object> claims;

    public MyOidcUserInfo(Map<String, Object> claims) {
        super(claims);
        Assert.notEmpty(claims, "claims cannot be empty");
        this.claims = Collections.unmodifiableMap(new LinkedHashMap(claims));
    }

    public Map<String, Object> getClaims() {
        return this.claims;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            MyOidcUserInfo that = (MyOidcUserInfo)obj;
            return this.getClaims().equals(that.getClaims());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.getClaims().hashCode();
    }

    public static MyOidcUserInfo.Builder myBuilder() {
        return new MyOidcUserInfo.Builder();
    }

    public static final class Builder {
        private final Map<String, Object> claims = new LinkedHashMap();

        private Builder() {
        }

        public MyOidcUserInfo.Builder claim(String name, Object value) {
            this.claims.put(name, value);
            return this;
        }

        public MyOidcUserInfo.Builder claims(Consumer<Map<String, Object>> claimsConsumer) {
            claimsConsumer.accept(this.claims);
            return this;
        }

        public MyOidcUserInfo.Builder username(String username) {
            return this.claim("username", username);
        }

        public MyOidcUserInfo.Builder name(String name) {
            return this.claim("name", name);
        }

        public MyOidcUserInfo.Builder description(String description) {
            return this.claim("description", description);
        }

        public MyOidcUserInfo.Builder status(Integer status) {
            return this.claim("status", status);
        }

        public MyOidcUserInfo.Builder phoneNumber(String phoneNumber) {
            return this.claim("phone_number", phoneNumber);
        }

        public MyOidcUserInfo.Builder email(String email) {
            return this.claim("email", email);
        }

        public MyOidcUserInfo.Builder profile(String profile) {
            return this.claim("profile", profile);
        }

        public MyOidcUserInfo.Builder address(String address) {
            return this.claim("address", address);
        }

        public MyOidcUserInfo build() {
            return new MyOidcUserInfo(this.claims);
        }

    }
}
