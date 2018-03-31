package com.hui.vedio_service.vedio.entity.vo;

import lombok.Data;

@Data
public class UserBaseVo {

    private Long id;

    /**
     * 名字
     */
    private String name;

    private String headImg;
}
