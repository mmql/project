package com.example.demo.service;

import com.example.demo.orm.dao.URoleMapper;
import com.example.demo.orm.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private URoleMapper uRoleMapper;

    public List<SysRole> getRolesByUser(int userId) {
        return uRoleMapper.getRolesByUser(userId);
    }
}

