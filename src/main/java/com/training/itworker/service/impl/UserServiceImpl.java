package com.training.itworker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.training.itworker.common.MyException;
import com.training.itworker.common.R;
import com.training.itworker.common.ResponseEnum;
import com.training.itworker.entity.User;
import com.training.itworker.mapper.UserMapper;
import com.training.itworker.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public R<String> getByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        queryWrapper.select("name", "image", "statement");
        User user = userMapper.selectOne(queryWrapper);

        if(user == null) {
            throw new MyException(ResponseEnum.ERROR.getCode(), "该用户不存在！");
        }

        return R.ok(user.toString());
    }
}
