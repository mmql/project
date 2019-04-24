package com.example.demo.orm.dao;

import com.example.demo.orm.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface URoleMapper {

    @Select("select * from sys_role where role_id in (select role_id from sys_user_role where user_id=#{userId} )")
    @Results(id = "role", value = {
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name")
    })
    List<SysRole> getRolesByUser(int userId);

}