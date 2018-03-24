package com.hui.user_service.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_role")
@Data
public class Role extends LogicOpEntity<Role> {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 名字
     */
    @TableField(value = "name")
    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}