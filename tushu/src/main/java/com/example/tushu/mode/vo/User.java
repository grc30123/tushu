package com.example.tushu.mode.vo;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


//失败用法 并没有增加 返回用户ID和phone的功能
public class User extends org.springframework.security.core.userdetails.User {

    private long idUser;
    private long phone;

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities, long idUser, long phone) {
        super(username, password, authorities);
        this.idUser = idUser;
        this.phone = phone;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

}
