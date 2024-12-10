package com.training.itworker.service;

import com.training.itworker.common.R;
import com.training.itworker.entity.User;

public interface AuthService {
    R<String> login(String name, String password);

    R<String> register(User user);

    void logout();
}
