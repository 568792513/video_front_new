package com.hui.comment_service.service;

import com.hui.comment_service.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("vedio-service")
public interface VideoFeignService {
    @RequestMapping(value = "/api/web/video/addCommentCount", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    Integer addCommentCount(@RequestParam("videoId") Long videoId);
}
