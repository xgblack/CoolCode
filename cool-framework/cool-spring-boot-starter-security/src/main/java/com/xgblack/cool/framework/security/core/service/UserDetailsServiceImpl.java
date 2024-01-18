package com.xgblack.cool.framework.security.core.service;

import cn.hutool.core.util.ArrayUtil;
import com.xgblack.cool.framework.common.constants.SecurityConstants;
import com.xgblack.cool.framework.security.dto.SysUser;
import com.xgblack.cool.framework.security.dto.UserInfo;
import com.xgblack.cool.framework.security.service.RemoteUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo info = remoteUserService.info(username, null);
        return getUserDetails(info);
    }



    /**
     * TODO
     * 构建userdetails
     * @param info 用户信息
     * @return UserDetails
     */
    private UserDetails getUserDetails(UserInfo info) {
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
        //TODO:使用真正的权限
        //Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        List<SimpleGrantedAuthority> authorities = Arrays.asList("USER").stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        SysUser user = info.getSysUser();

        // 构造security用户
        return new User(user.getUsername(),user.getPassword(),authorities);
    }
}
