package com.hui.vedio_service.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(value={"password"}) //希望动态过滤掉的属性
@TableName("t_user")
@Data
public class User extends LogicOpEntity<User>{
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

}
