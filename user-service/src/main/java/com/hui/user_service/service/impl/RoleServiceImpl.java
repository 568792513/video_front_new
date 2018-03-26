package com.hui.user_service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hui.user_service.entity.Role;
import com.hui.user_service.entity.User;
import com.hui.user_service.entity.UserRole;
import com.hui.user_service.mapper.RoleMapper;
import com.hui.user_service.mapper.UserRoleMapper;
import com.hui.user_service.service.RoleService;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements
        RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getRolesByUser(User user) {
        if (user != null) {
            return roleMapper.getRolesByUser(user);
        }
        return null;
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.selectList(null);
    }

    @Override
    public Role getRolesByRoleName(String name) {
        EntityWrapper<Role> wrapper = new EntityWrapper<>();
        wrapper.where("role_name={0}", name);
        return roleMapper.selectList(wrapper).get(0);
    }

    @Transactional
    @Override
    public Boolean setRole(User user, List<Role> roles) {
        if (user == null || CollectionUtils.isEmpty(roles)) {
            return false;
        } else {
            List<Role> rolesList = this.getRoles();
            // 判断表中是否具有该角色
            for (Role role : roles) {
                if (!rolesList.contains(role)) {
                    return false;
                }
            }
            //先删除用户原有角色
            EntityWrapper<UserRole> wrapper = new EntityWrapper<>();
            wrapper.where("user_id={0}", user.getId());
            userRoleMapper.delete(wrapper);
            //设置角色
            for (Role role : roles) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(role.getId());
                userRole.setUserId(user.getId());
                userRoleMapper.insert(userRole);
            }
            return true;
        }
    }
}
