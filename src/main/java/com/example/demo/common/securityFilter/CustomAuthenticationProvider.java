package com.example.demo.common.securityFilter;

import com.example.demo.util.MD5Util;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;


public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("LOGIN_USERNAME_NULL");
        }
        if (null == authentication.getCredentials()) {
            throw new UsernameNotFoundException("LOGIN_PASSWORD_NULL");
        }
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(userDetails == null) {
            throw new UsernameNotFoundException("LOGIN_USERNAME_NOT_FOUND");
        }else if(!userDetails.getPassword().equals(MD5Util.MD5(password))){
            throw new DisabledException("LOGIN_PASSWORD_ERROR");
        } else if (!userDetails.isEnabled()){
            throw new DisabledException("用户已被禁用");
        }else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        }else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("账号已被锁定");
        }else if (!userDetails.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期");
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}