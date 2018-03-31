package com.hui.vedio_service.vedio.service;

import com.hui.vedio_service.vedio.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@FeignClient("user-service")
public interface UserFeignService {
    @RequestMapping(value = "/api/web/user/getUserById", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    User getUserById(@RequestParam("userId") Long userId);
}
