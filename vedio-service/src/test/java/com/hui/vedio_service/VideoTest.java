package com.hui.vedio_service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hui.video_service.VedioApplication;
import com.hui.video_service.entity.Video;
import com.hui.video_service.service.IVedioService;
import com.hui.video_service.mapper.VedioMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = VedioApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)

public class VideoTest {

    @Resource
    private IVedioService vedioService;

    @Resource
    private VedioMapper vedioMapper;

    @Test
    public void selectPage(){
        Page<Video> page = new Page<>();
        page.setCurrent(1);
        page.setSize(2);
        System.out.println(vedioService.selectVideoPage(page, 977790447495798786L));
        System.out.println(vedioService.selectVideoPage(page, 977790447495798786L).getRecords());
    }

    @Test
    public void test(){
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.where("del_flag={0}", 0);
        List<Video> vedioList = vedioMapper.selectList(wrapper);
        vedioList.forEach(n -> {
            System.out.println(n.getName());
        });
        vedioList.sort(new Comparator<Video>() {
            @Override
            public int compare(Video dt1, Video dt2) {
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    if (dt1.getCreateTime().getTime() > dt2.getCreateTime().getTime()) {
                        return -1;
                    } else if (dt1.getCreateTime().getTime() < dt2.getCreateTime().getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        System.out.println("----------------");
        vedioList.forEach(n -> {
            System.out.println(n.getName());
        });
        List<Video> collect = vedioList.stream().filter(n -> n.getType() == 0).limit(8).collect(Collectors.toList());
        System.out.println("----------------");
        collect.forEach(n -> System.out.println(n.getName()));
    }
}
