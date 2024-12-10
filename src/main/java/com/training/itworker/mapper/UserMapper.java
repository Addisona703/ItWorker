package com.training.itworker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.training.itworker.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
