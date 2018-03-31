package com.hui.user_service.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.hui.user_service.entity.User;
import com.hui.user_service.service.UserService;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CookieUtils {

    private static UserService userService;

    @Resource
    private UserService userServiceTmp;

    @PostConstruct
    public void init() {
        userService = this.userServiceTmp;
    }

    // 从header获取user
    public static User getUserByToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (StringUtils.isNullOrEmpty(token)){
            return null;
        } else {
            String regEx="[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(token);
            String id =  m.replaceAll("").trim();
            Long Id = Long.valueOf(id);
            return userService.findUserById(Id);
        }
    }
}
