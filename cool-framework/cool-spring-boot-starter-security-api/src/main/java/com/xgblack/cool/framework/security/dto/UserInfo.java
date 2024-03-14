package com.xgblack.cool.framework.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

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
    private Set<String> permissions;

    /**
     * 角色集合
     */
    private Set<Long> roles;
}
