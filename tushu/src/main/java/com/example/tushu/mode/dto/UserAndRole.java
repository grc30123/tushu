package com.example.tushu.mode.dto;

import com.example.tushu.entity.User;

public class UserAndRole extends User {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
