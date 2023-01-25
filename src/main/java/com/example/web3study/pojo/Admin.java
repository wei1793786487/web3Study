package com.example.web3study.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class Admin implements UserDetails {
    /**
     * 管理员id
     */
    private Integer id;

    /**
     * 管理员名称
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 管理员密码
     */
    @JsonIgnore
    private String password;

    /**
     * 上次登录的ip
     */
    private String ip;

    /**
     * 上次登录的地址
     */
    private String address;

    /**
     * 上次登录的时间
     */
    private Date lasttime;

    /**
     * 状态 默认为1 0为不可用
     */
    private Integer status;

    /**
     * 区块链的user信息id
     */
    private Integer buid;

    private BlockchainUser blockchainUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + "ADMIN"));
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status==1;
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