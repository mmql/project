package com.example.demo.service;

import com.example.demo.orm.dao.SysUserMapper;
import com.example.demo.orm.model.SysUser;
import com.example.demo.orm.model.SysUserExample;
import com.example.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private SysUserMapper userMapper;

    @Transactional
    public boolean register(String userName, String password) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        List<SysUser> users = userMapper.selectByExample(example);
        if (0 != users.size()) {
            return false;
        }
        SysUser user = new SysUser();
        user.setUserName(userName);
        user.setPassword(MD5Util.MD5(password));
        user.setUserStatus((byte) 0);
        userMapper.insertSelective(user);
        return true;
    }

    public SysUser getUserByUsername(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUserNameEqualTo(username);
        SysUser user = userMapper.selectByExample(example).get(0);
        return user;
    }
}

