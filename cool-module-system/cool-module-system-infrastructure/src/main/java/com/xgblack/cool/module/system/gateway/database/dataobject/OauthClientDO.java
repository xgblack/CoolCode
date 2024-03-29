package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.dataobject.BaseDO;
import com.xgblack.cool.framework.mybatis.type.StringListTypeHandler;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_oauth_client_detail")
public class OauthClientDO extends BaseDO {//TODO:看是否适配多租户

    @Serial
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
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
    @Column(typeHandler = StringListTypeHandler.class )
    private List<String> authorizedGrantTypes;

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

    /**
     * 是否删除
     */
    @Column(isLogicDelete = true)
    private Boolean deleted;


}
