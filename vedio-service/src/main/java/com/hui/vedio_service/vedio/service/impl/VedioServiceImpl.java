package com.hui.vedio_service.vedio.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hui.vedio_service.vedio.entity.Vedio;
import com.hui.vedio_service.vedio.mapper.VedioMapper;
import com.hui.vedio_service.vedio.service.IVedioService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class VedioServiceImpl extends ServiceImpl<VedioMapper, Vedio> implements IVedioService {

    @Resource
    private VedioMapper vedioMapper;

    @Override
    public List<Vedio> getAllVideoByUserId(Long userId) {
        EntityWrapper<Vedio> wrapper = new EntityWrapper<>();
        wrapper.where("user_id={0}", userId);
        List<Vedio> result = vedioMapper.selectList(wrapper);
        return result;
    }

    @Override
    public Page<Vedio> selectVideoPage(Page<Vedio> page, Long userId) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
        // page.setOptimizeCountSql(false);
        // 不查询总记录数
        // page.setSearchCount(false);
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.where("user_id={0}", userId);
        List<Vedio> list = vedioMapper.selectPage(page, wrapper);
        page.setTotal(list.size());
        page.setRecords(list);
        return page;
    }
}
