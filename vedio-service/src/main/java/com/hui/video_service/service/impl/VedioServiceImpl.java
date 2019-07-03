package com.hui.video_service.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hui.video_service.entity.User;
import com.hui.video_service.entity.Video;
import com.hui.video_service.mapper.VedioMapper;
import com.hui.video_service.service.IVedioService;
import com.hui.video_service.service.UserFeignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class VedioServiceImpl extends ServiceImpl<VedioMapper, Video> implements IVedioService {

    @Resource
    private VedioMapper vedioMapper;

    @Resource
    private UserFeignService userFeignService;

    @Override
    public List<Video> getAllVideoByUserId(Long userId) {
        EntityWrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.where("user_id={0}", userId);
        List<Video> result = vedioMapper.selectList(wrapper);
        return result;
    }

    @Override
    public Page<Video> selectVideoPage(Page<Video> page, Long userId) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
        // page.setOptimizeCountSql(false);
        // 不查询总记录数
        // page.setSearchCount(false);
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.where("user_id={0}", userId);
        List<Video> list = vedioMapper.selectPage(page, wrapper);
        page.setTotal(list.size());
        page.setRecords(list);
        return page;
    }

    @Override
    public User getUserByVideoId(Long videoId) {
        Video vedio = vedioMapper.selectById(videoId);
        if (vedio == null){
            return null;
        }
        return userFeignService.getUserById(vedio.getUserId());
    }
}
