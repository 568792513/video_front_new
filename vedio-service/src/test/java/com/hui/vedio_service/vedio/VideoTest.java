package com.hui.vedio_service.vedio;

import com.baomidou.mybatisplus.plugins.Page;
import com.hui.vedio_service.vedio.entity.Vedio;
import com.hui.vedio_service.vedio.service.IVedioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = VedioApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)

public class VideoTest {

    @Resource
    private IVedioService vedioService;

    @Test
    public void selectPage(){
        Page<Vedio> page = new Page<>();
        page.setCurrent(1);
        page.setSize(2);
        System.out.println(vedioService.selectVideoPage(page, 977790447495798786L));
        System.out.println(vedioService.selectVideoPage(page, 977790447495798786L).getRecords());
    }
}
