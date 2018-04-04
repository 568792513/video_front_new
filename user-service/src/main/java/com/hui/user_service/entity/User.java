package com.hui.user_service.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties(value={"password"}) //希望动态过滤掉的属性
@TableName("t_user")
@Data
public class User extends LogicOpEntity<User> implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 名字
     */
    @TableField(value = "name")
    private String name;

    /**
     * 密码
     */
    @JsonIgnore
    @TableField(value = "password")
    private String password;

    @TableField("head_img")
    private String headImg;

    @TableField("name_in_ftp")
    private String nameInFtp;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.name;
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
