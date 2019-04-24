package com.example.demo.controller;

import com.example.demo.common.templete.RestResponse;
import com.example.demo.orm.model.SysUser;
import com.example.demo.service.UserService;
import com.example.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public RestResponse getUserInfo(@RequestParam String username, @RequestParam String password) {
        SysUser user = userService.getUserByUsername(username);
        if (null == user) {
            return RestResponse.error("USERNAME_NOT_EXISTS");
        }
        if (!user.getPassword().equals(MD5Util.MD5(password))) {
            return RestResponse.error("PASSWORD_ERROR");
        }
        return RestResponse.ok(user);
    }

    @RequestMapping(value = "/aa", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public RestResponse aaa() {
        return RestResponse.ok("sadsad");
    }

}
