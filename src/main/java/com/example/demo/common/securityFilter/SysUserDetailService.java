package com.example.demo.common.securityFilter;

import com.example.demo.orm.model.SysUser;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class SysUserDetailService implements UserDetailsService, Serializable {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDetails sysUserDetails = new SysUserDetails();
        SysUser user = userService.getUserByUsername(username);
        if (null == user) {
            return sysUserDetails;
        }
        sysUserDetails.setPassword(user.getPassword());

        sysUserDetails.setRoles(roleService.getRolesByUser(user.getUserId()));
        return sysUserDetails;
    }
}
