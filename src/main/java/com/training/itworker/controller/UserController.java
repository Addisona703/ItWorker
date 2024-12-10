package com.training.itworker.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.training.itworker.common.MyException;
import com.training.itworker.common.R;
import com.training.itworker.common.ResponseEnum;
import com.training.itworker.entity.User;
import com.training.itworker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/** 用户功能 **/

@RestController
@RequestMapping("/user")
@Tag(name = "用户功能", description = "对用户进行管理，增删改查等操作")
public class UserController {

    @Resource
    private UserService userService;

    /** 查找姓名 **/
    @GetMapping("/queryByName/{name}")
    @Operation(summary = "查找指定姓名的用户")
    public R<String> getByName(@PathVariable("name") String name) {
        return userService.getByName(name);
    }

    /** 查找id **/
    @GetMapping("/queryById/{id}")
    @Operation(summary = "查找指定id的用户")
    public R<String> getById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        if(user == null) {
            throw new MyException(ResponseEnum.ERROR.getCode(), "该用户不存在！");
        }

        return R.ok(user.toString());
    }

    /** 修改用户信息 **/
    @PostMapping("/updateUserInfo")
    @Operation(summary = "修改用户信息")
    public R<String> updateUserInfo(@RequestBody User user) {
        try {
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", user.getId());

            boolean update = userService.update(user, updateWrapper);

            return update ? R.ok("更新成功！") : R.exception(ResponseEnum.ERROR.getCode(), "操作失败，用户不存在！");
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResponseEnum.ERROR.getCode(), "修改失败！" + e.getMessage());
        }
    }

    /** 需要解决的问题：只有当前用户才能进行删除操作，可以通过传入token，然后解析token获取用户id，然后对用户进行验证 **/
    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "删除指定id的用户")
    public R<String> deleteById(@PathVariable Integer id) {
        // 调用 service 层删除用户
        boolean check = userService.removeById(id);

        // 如果删除失败，抛出自定义异常
        if (!check) {
            throw new MyException(ResponseEnum.ERROR.getCode(), "删除失败！");
        }

        return R.ok("删除成功！");
    }

    /** 更新简介 **/
    @PostMapping("/updateStatement")
    @Operation(summary = "更新简介")
    public R<String> updateStatement(@RequestBody User user) {
        boolean check = userService.update(user, new UpdateWrapper<User>().eq("id", user.getId()));
        if(!check) {
            throw new MyException(ResponseEnum.FAIL.getCode(), "更新失败！");
        }

        return R.ok("更新成功！");
    }
}
