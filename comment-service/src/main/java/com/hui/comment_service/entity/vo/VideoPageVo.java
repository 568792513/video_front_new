package com.hui.comment_service.entity.vo;

import com.baomidou.mybatisplus.plugins.Page;
import com.hui.comment_service.entity.Vedio;
import lombok.Data;

@Data
public class VideoPageVo {
    private Page<Vedio> page;
    private Integer total;

}
