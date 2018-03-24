package com.hui.user_service.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hui.user_service.entity.Role;
import com.hui.user_service.entity.User;
import com.hui.user_service.mapper.RoleMapper;
import com.hui.user_service.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements
        RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRolesByUser(User user) {
        if (user != null) {
            return roleMapper.getRolesByUser(user);
        }
        return null;
    }
}
