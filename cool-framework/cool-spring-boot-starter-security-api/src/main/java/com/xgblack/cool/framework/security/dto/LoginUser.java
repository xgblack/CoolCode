package com.xgblack.cool.framework.security.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.io.Serial;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 扩展用户信息
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */


@ToString
public class LoginUser extends User implements OAuth2AuthenticatedPrincipal {

    @Serial
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 扩展属性，方便存放oauth 上下文相关信息
     */
    private final Map<String, Object> attributes = new HashMap<>();

    /**
     * 用户编号
     */
    @Getter
    private final Long id;

    @Getter
    private final Long deptId;

    /**
     * 手机号
     */
    @Getter
    private final String phone;

    /**
     * 租户编号
     */
    @Getter
    private final Long tenantId;

    public LoginUser(Long id, Long deptId, Long tenantId, String username, String password, String phone, boolean enabled,
                     boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                     Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
        this.tenantId = tenantId;
        this.phone = phone;
    }


    /**
     * Get the OAuth 2.0 token attributes
     * @return the OAuth 2.0 token attributes
     */
    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getName() {
        return this.getUsername();
    }
}
