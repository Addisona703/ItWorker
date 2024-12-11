package com.training.itworker.controller;

import com.training.itworker.common.PassToken;
import com.training.itworker.common.R;
import com.training.itworker.service.AuthService;
import com.training.itworker.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 *  登录、登出、注册 等功能
 * */

@RestController
@RequestMapping("/auth")
@Tag(name = "账号管理功能", description = "登入、登出、注册功能")
public class AuthController {

    @Resource
    private AuthService authService;

    /**
     *  登录功能
     * */
    @PassToken
    @PostMapping("/login")
    @Operation(summary = "登录功能")
    public R<String> login(@RequestBody User user) {
/*
         这里可以进行用户验证逻辑
        String token = authService.login(user.getName(), user.getPassword());

        return R.ok(token, "登录成功！");
*/
        return authService.login(user.getName(), user.getPassword());
    }

    /** 注册功能 **/
    @PassToken
    @PostMapping("/register")
    @Operation(summary = "注册功能")
    public R<String> register(@RequestBody User user) {
        // 这里可以进行用户注册逻辑
        return authService.register(user);
    }

    /** 登出功能 **/
    @PostMapping("/logout")
    @Operation(summary = "登出功能")
    public R<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        // 处理用户登出逻辑
        return authService.logout(token);
    }
}
