package com.xgblack.framework.security.core.api.dto;

import com.xgblack.framework.security.dto.SysUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户信息
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserInfo implements Serializable {
    /**
     * 用户基本信息
     */
    private SysUser sysUser;

    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private Long[] roles;
}
