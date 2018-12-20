package com.dream.feznotepad.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Date;

/**
 * Created by H.J
 * 2018/12/12
 */
@Data
@Entity
public class WebUserDetail implements UserDetails {

    @Id
    @Column(nullable = false)
    private String userId;

    @Column
    private String username;

    //密码，小程序用户为openID
    @Column(length = 500,nullable = false)
    private String password;

    //创建时间yyyy-MM-dd HH:mm:ss
    @Column(nullable = false)
    private Date createTime = new Date();

    //最近登录时间
    @Column(nullable = false)
    private Date optTime = new Date();

    public WebUserDetail(){
        super();
    }

    public WebUserDetail(String userId){
        this.userId = userId;
    }

    public WebUserDetail(String userId,String password,String username){
        this.userId = userId;
        this.password = password;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
