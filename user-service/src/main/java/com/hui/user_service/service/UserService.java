package com.hui.user_service.service;

import com.baomidou.mybatisplus.service.IService;
import com.hui.user_service.entity.User;

public interface UserService extends IService<User> {


    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    Integer saveUser(User user);

    /**
     * 通过用户名查找用户
     *
     * @param name
     * @return
     */
    User findUserByName(String name);

}
