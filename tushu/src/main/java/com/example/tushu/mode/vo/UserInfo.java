package com.example.tushu.mode.vo;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserInfo {

    private long idUser;
    private String account;
    private String role;
    private Collection<GrantedAuthority> authorities;
    public UserInfo(long idUser, String account, String role, Collection<GrantedAuthority> authorities) {
        this.idUser = idUser;
        this.account = account;
        this.role = role;
        this.authorities = authorities;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


}
