package com.example.demo.common.securityFilter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUserHolder {
    public static String getUserName() {
        Authentication authentication = SecurityContextHolder
          .getContext()
          .getAuthentication();

        if (null == authentication) return null;

        return authentication.getName();
    }

}
