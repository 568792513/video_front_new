package com.hui.user_service.service;

import com.baomidou.mybatisplus.service.IService;
import com.hui.user_service.entity.Role;
import com.hui.user_service.entity.User;

import java.util.List;

public interface RoleService extends IService<Role> {
    /**
     * 通过User 的 id 或 name 获取role
     * @param user
     * @return
     */
    List<Role> getRolesByUser(User user);
}
