//package com.hui.user_service.common.config.security;
//
//import com.hui.user_service.entity.Role;
//import com.hui.user_service.entity.User;
//import com.hui.user_service.mapper.RoleMapper;
//import com.hui.user_service.service.UserService;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
////自定义UserDetailsService 接口
//@Service
//public class CustomUserService implements UserDetailsService {
//
//    @Resource
//    private UserService userService;
//
//    @Resource
//    private RoleMapper roleMapper;
//
//    //重写loadUserByUsername 方法获得 userdetails 类型用户
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//
//        User user = userService.findUserByName(username);
//        if(user == null){
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        List<Role> roles = roleMapper.getRolesByUser(user);
//        if (!CollectionUtils.isEmpty(roles)) {
//            for(Role role : roles)
//            {
//                authorities.add(new SimpleGrantedAuthority(role.getName()));
//                System.out.println(role.getName());
//            }
//        }
//        return new org.springframework.security.core.userdetails.User(user.getName(),
//                user.getPassword(), authorities);
//    }
//}