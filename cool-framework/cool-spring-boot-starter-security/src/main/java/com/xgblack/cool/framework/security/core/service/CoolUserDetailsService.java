package com.xgblack.cool.framework.security.core.service;

import cn.hutool.core.util.ArrayUtil;
import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.cool.framework.security.dto.SysUser;
import com.xgblack.cool.framework.security.dto.UserInfo;
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
     * 构建user details
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

        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        // 构造security用户 TODO
        //由于密码默认使用BCRYPT加密，所以这里需要添加前缀，DelegatingPasswordEncoder能够根据密码前缀来确定密码编码器
        return new LoginUser(user.getId(), user.getDeptId(), user.getTenantId(), user.getUsername(),
                SecurityConstants.BCRYPT + user.getPassword(), user.getPhone(), true, true, true,
                !user.getLocked(), authorities);
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
