package com.hui.user_service.service;

import com.baomidou.mybatisplus.service.IService;
import com.hui.user_service.entity.User;

public interface UserService extends IService<User> {


    /**
     * 简单保存用户
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

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 注册用户 带角色
     * @param user
     * @return
     */
    Boolean register(User user);

    /**
     * 修改密码
     * @param user
     * @param password
     * @return
     */
    Boolean changePassword(User user, String password);



}
