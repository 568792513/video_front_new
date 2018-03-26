package com.hui.user_service.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_role")
@Data
public class Role {

    // 普通用户
    public static final String ROLE_USER = "ROLE_USER";

    // 管理员
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    // 会员
    public static final String ROLE_VIP = "ROLE_VIP";


    private Long id;

    /**
     * 名字
     */
    @TableField(value = "role_name")
    private String name;
}