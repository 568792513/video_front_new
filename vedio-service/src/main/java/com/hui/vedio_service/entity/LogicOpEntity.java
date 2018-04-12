package com.hui.vedio_service.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.util.Date;

/* import com.baomidou.mybatisplus.enums.FieldFill; */

/**
 * 具有业务逻辑性实体
 *
 * @param <T>
 * @author ZhenJin
 */
public abstract class LogicOpEntity<T> extends Model<LogicOpEntity<T>> {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     **/
    @TableField(value = "create_time")
    // , fill = FieldFill.INSERT
    private Date createTime;

    /**
     * 更新时间
     **/
    @TableField(value = "update_time")
    // , fill = FieldFill.UPDATE
    private Date updateTime;

    /**
     * 删除标识(0.正常.1删除)
     **/
    @TableLogic
    @TableField("del_flag")
    private Integer delFlag;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

}

