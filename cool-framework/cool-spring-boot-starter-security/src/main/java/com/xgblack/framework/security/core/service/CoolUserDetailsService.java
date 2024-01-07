package com.xgblack.framework.security.core.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.framework.security.constans.CommonConstants;
import com.xgblack.framework.security.dto.SysUser;
import com.xgblack.framework.security.dto.UserInfo;
import org.springframework.core.Ordered;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

public interface CoolUserDetailsService extends UserDetailsService, Ordered {

    /**
     * 是否支持此客户端校验
     * @param clientId 目标客户端
     * @return true/false
     */
    default boolean support(String clientId, String grantType) {
        return true;
    }

    /**
     * 排序值 默认取最大的
     * @return 排序值
     */
    default int getOrder() {
        return 0;
    }

    /**
     * TODO
     * 构建userdetails
     * @param info 用户信息
     * @return UserDetails
     */
    default UserDetails getUserDetails(UserInfo info) {
        if (info == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Set<String> dbAuthsSet = new HashSet<>();

        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }

        Collection<GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        // 构造security用户
        return new LoginUser(user.getUserId(),null, user.getDeptId(),null, user.getUsername(),
                SecurityConstants.BCRYPT + user.getPassword(), user.getPhone(), true, true, true,
                StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL), authorities);
    }

    /**
     * 通过用户实体查询
     * @param user 用户
     * @return
     */
    default UserDetails loadUserByUser(LoginUser user) {
        return this.loadUserByUsername(user.getUsername());
    }

}
