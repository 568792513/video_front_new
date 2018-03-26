package com.hui.user_service.service.impl;

import com.hui.user_service.entity.Role;
import com.hui.user_service.entity.User;
import com.hui.user_service.service.RoleService;
import com.hui.user_service.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Transactional
@Service
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    /**
     * 根据用户名获取登录用户信息
     *
     * @param name
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername( String name) throws UsernameNotFoundException {
        User user = userService.findUserByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("用户名：" + name + "不存在！");
        }
        return user;
//        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
//        List<Role> roleList =  roleService.getRolesByUser(user);
//        List<String> stringList = new ArrayList<>();
//        for (Role role : roleList) {
//            stringList.add(role.getName());
//        }
//        Iterator<String> iterator = stringList.iterator();
//        while (iterator.hasNext()) {
//            collection.add(new SimpleGrantedAuthority(iterator.next()));
//        }
//        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), collection);

    }


}
