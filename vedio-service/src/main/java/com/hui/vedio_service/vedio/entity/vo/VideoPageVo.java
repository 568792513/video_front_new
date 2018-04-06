package com.hui.vedio_service.vedio.entity.vo;

import com.baomidou.mybatisplus.plugins.Page;
import com.hui.vedio_service.vedio.entity.Vedio;
import lombok.Data;

@Data
public class VideoPageVo {
    private Page<Vedio> page;
    private Integer total;

}
