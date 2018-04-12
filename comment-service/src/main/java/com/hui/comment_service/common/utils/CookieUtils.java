package com.hui.comment_service.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CookieUtils {

    // 从header获取userID
    public static Long getUserIdByToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)){
            return null;
        } else {
            String regEx="[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(token);
            String id =  m.replaceAll("").trim();
            Long Id = Long.valueOf(id);
            return Id;
        }
    }
}
