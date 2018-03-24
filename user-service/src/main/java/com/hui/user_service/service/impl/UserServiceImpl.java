package com.hui.user_service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hui.user_service.entity.User;
import com.hui.user_service.mapper.UserMapper;
import com.hui.user_service.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements
        UserService {
    @Resource
    private UserMapper userMapper;

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
}
