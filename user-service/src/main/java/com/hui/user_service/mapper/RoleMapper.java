package com.hui.user_service.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hui.user_service.entity.Role;
import com.hui.user_service.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper  extends BaseMapper<Role>{
    List<Role> getRolesByUser(User user);
}
