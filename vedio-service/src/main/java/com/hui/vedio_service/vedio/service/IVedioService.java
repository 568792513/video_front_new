package com.hui.vedio_service.vedio.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hui.vedio_service.vedio.entity.Vedio;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IVedioService extends IService<Vedio> {

	/**
	 * 通过userId获取所有视频
	 * @param userId
	 * @return
	 */
	List<Vedio> getAllVideoByUserId(Long userId);

	Page<Vedio> selectVideoPage(Page<Vedio> page, Long userId);

}
