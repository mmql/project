package com.example.demo.orm.model;

public class SysRolePermission {
    private Integer srpId;

    private Integer roleId;

    private Integer perId;

    public Integer getSrpId() {
        return srpId;
    }

    public void setSrpId(Integer srpId) {
        this.srpId = srpId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }
}