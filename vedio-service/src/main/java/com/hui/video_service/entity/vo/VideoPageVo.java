package com.hui.video_service.entity.vo;

import com.baomidou.mybatisplus.plugins.Page;
import com.hui.video_service.entity.Video;
import lombok.Data;

@Data
public class VideoPageVo {
    private Page<Video> page;
    private Integer total;

}
