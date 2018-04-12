package com.hui.vedio_service.entity.vo;

import com.baomidou.mybatisplus.plugins.Page;
import com.hui.vedio_service.entity.Vedio;
import lombok.Data;

@Data
public class VideoPageVo {
    private Page<Vedio> page;
    private Integer total;

}
