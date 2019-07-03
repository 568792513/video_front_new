package com.hui.video_service.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hui.video_service.entity.User;
import com.hui.video_service.entity.Video;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IVedioService extends IService<Video> {

	/**
	 * 通过userId获取所有视频
	 * @param userId
	 * @return
	 */
	List<Video> getAllVideoByUserId(Long userId);

	Page<Video> selectVideoPage(Page<Video> page, Long userId);

	/**
	 * 通过视频ID获取用户
	 * @param videoId
	 * @return
	 */
	User getUserByVideoId(Long videoId);

}
