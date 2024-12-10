package com.training.itworker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.training.itworker.common.R;
import com.training.itworker.entity.User;

public interface UserService extends IService<User> {
    R<String> getByName(String name);
}
