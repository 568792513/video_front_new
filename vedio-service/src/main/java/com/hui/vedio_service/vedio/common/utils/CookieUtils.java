package com.hui.vedio_service.vedio.common.utils;

import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Component;

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
        if (StringUtils.isNullOrEmpty(token)){
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
