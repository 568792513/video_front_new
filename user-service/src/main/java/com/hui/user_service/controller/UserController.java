package com.hui.user_service.controller;

import com.hui.user_service.common.Result;
import com.hui.user_service.entity.User;
import com.hui.user_service.mapper.UserMapper;
import com.hui.user_service.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:8081", maxAge = 3600)
@RestController
@RequestMapping("api/web/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<User> save(User user) {
        userService.saveUser(user);
        return new Result<>(0, "", "保存成功", user);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public Result<User> getUsers(User user) {
        List<User> userList =  userMapper.selectList(null);
        return new Result(0, "", "", userList);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<User> register(User user) {
        if (StringUtils.isNotBlank(user.getName())) {
            User userInDb = userService.findUserByName(user.getName());
            if (userInDb == null) {
                userService.saveUser(user);
                return new Result<>(Result.SUCCESS, "", "注册成功", user);
            } else {
                return new Result<>(Result.PARAM_VALIDATE_FAILED, "", "用户名已存在", user);
            }
        }
        return new Result<>(Result.PARAM_ERROR, "", "参数错误", user);
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public Result<User> login(@RequestParam("username")String username, @RequestParam("password")String password) {
//        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
//            User userInDb = userService.findUserByName(username);
//            if (userInDb == null) {
//                userService.saveUser(user);
//                return new Result<>(Result.SUCCESS, "", "注册成功", user);
//            } else {
//                return new Result<>(Result.PARAM_VALIDATE_FAILED, "", "用户名已存在", user);
//            }
//        }
//        return new Result<>(Result.PARAM_ERROR, "", "参数错误", user);
//    }

    /**
     * 如果自动跳转到这个页面，说明用户未登录，返回相应的提示即可
     * <p>
     * 如果要支持表单登录，可以在这个方法中判断请求的类型，进而决定返回JSON还是HTML页面
     *
     * @return
     */
    @RequestMapping("/login_page")
    public Result loginPage() {
        return new Result(Result.SUCCESS, "error", "尚未登录，请登录!");
    }

}
