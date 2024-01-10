package com.xgblack.framework.security.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户端信息
 * TODO：可以与DTO合并
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Data
public class SysOauthClientDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 资源ID
     */
    private String resourceIds;

    /**
     * 作用域
     */
    private String scope;

    /**
     * 授权方式[A,B,C]
     */
    private String[] authorizedGrantTypes;

    /**
     * 回调地址
     */
    private String webServerRedirectUri;

    /**
     * 权限
     */
    private String authorities;

    /**
     * 请求令牌有效时间
     */
    private Integer accessTokenValidity;

    /**
     * 刷新令牌有效时间
     */
    private Integer refreshTokenValidity;

    /**
     * 扩展信息
     */
    private String additionalInformation;

    /**
     * 是否自动放行
     */
    private String autoapprove;

    //private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建者id
     */
    private Long creator;
    /**
     * 更新者id
     */
    private Long updater;

}
