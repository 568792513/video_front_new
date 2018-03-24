package com.hui.user_service.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hui.user_service.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
