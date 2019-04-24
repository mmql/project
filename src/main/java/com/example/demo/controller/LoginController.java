package com.example.demo.controller;

import com.example.demo.common.templete.RestResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestResponse register(@RequestParam String username, @RequestParam String password) {
        if (StringUtils.isEmpty(username)) {
            return RestResponse.error("REGISTER_USERNAME_NULL");
        }
        if (StringUtils.isEmpty(password)) {
            return RestResponse.error("REGISTER_PASSWORD_NULL");
        }
        if (userService.register(username, password)) {
            return RestResponse.ok(null);
        }
        return RestResponse.error("REGISTER_USERNAME_EXISTS");
    }

}
