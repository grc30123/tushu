package com.example.tushu.mode.dto;

import com.example.tushu.entity.Resource;

public class RoleAndResource extends Resource {
    private String roleName;
    private String roleResourceId;

    public String getRoleResourceId() {
        return roleResourceId;
    }

    public void setRoleResourceId(String roleResourceId) {
        this.roleResourceId = roleResourceId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
