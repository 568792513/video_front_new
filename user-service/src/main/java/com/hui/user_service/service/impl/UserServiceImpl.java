package com.hui.user_service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hui.user_service.entity.Role;
import com.hui.user_service.entity.User;
import com.hui.user_service.mapper.UserMapper;
import com.hui.user_service.service.RoleService;
import com.hui.user_service.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements
        UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    @Override
    public Integer saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User findUserByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("name", name);
        List<User> installmentUsers = this.selectList(wrapper);
        if (!CollectionUtils.isEmpty(installmentUsers)) {
            return installmentUsers.get(0);
        }
        return null;
    }

    @Transactional
    @Override
    public Boolean register(User user) {
        if (user == null) {
            return false;
        }
        //插入用户表
        userMapper.insert(user);
        // 设置角色
        List<Role> roleList = new ArrayList<>();
        Role role = roleService.getRolesByRoleName(Role.ROLE_USER);
        roleList.add(role);
        roleService.setRole(user, roleList);
        return true;
    }
}
