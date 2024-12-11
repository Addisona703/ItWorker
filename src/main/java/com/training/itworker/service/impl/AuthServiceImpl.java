package com.training.itworker.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.training.itworker.Util.JwtTokenUtils;
import com.training.itworker.common.MyException;
import com.training.itworker.common.R;
import com.training.itworker.common.ResponseEnum;
import com.training.itworker.entity.User;
import com.training.itworker.mapper.UserMapper;
import com.training.itworker.service.AuthService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param name
     * @param password
     * @return String
     * <p>返回Token给客户端，而不是存储在本地数据库，具体原因:<p/>
     * <oi>
     * <li>Token只用于验证身份，每次请求携带，请求是由前端发送</li>
     * <li>Token是临时的，过期或者撤销就不在有效，冗余的数据存储。</li>
     * <li>不存储 Token 可以减少数据库的敏感数据存储风险，降低安全漏洞的出现。</li>
     * </oi>
     */
    @Override
    public R<String> login(String name, String password) {
//         创建查询条件
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//         eq适合灵活处理，allEq适合批量处理
//        queryWrapper.eq("name", name).eq("password", password);
//        queryWrapper.allEq(Map.of("name", name, "password", password));

        // 查询单个用户，加上id，以防有相同名字的用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("name", name));

        // 判断用户是否存在
        if(user == null){
            throw new MyException(ResponseEnum.ERROR.getCode(), "该用户不存在！");
        }

        String storedHashedPassword = user.getPassword();

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), storedHashedPassword.toCharArray());

        if(result.verified){
            String token = JwtTokenUtils.getToken(String.valueOf(user.getId()));
            stringRedisTemplate.opsForValue().set(user.getName(), token, Duration.ofDays(2)); // 将token存到redis，后面这个日期需要一个特定的类型
            return R.ok(token, "登录成功！");
        } else {
            throw new MyException(401, "密码错误，请重新输入！");
        }

/*
         存储本地的情况，Optional是一个工具类，帮助我们避免显式地进行 null 检查和处理。
        Optional.ofNullable(user).ifPresent(user1 -> {
            String token = JwtTokenUtils.getToken(String.valueOf(user.getId()));
            user.setToken(token);
        });
        return JwtTokenUtils.getToken(String.valueOf(user.getId()));
*/
    }

    @Override
    @Transactional
    public R<String> register(User user) {
        // 查询已有用户
        User existUser = userMapper.selectOne(new QueryWrapper<User>().eq("name", user.getName()));

        if(existUser != null) {
            throw new MyException(ResponseEnum.ERROR.getCode(), "用户已存在！");
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(hashedPassword);

        try {
            userMapper.insert(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MyException(ResponseEnum.ERROR.getCode(), "注册失败，服务器内部错误！");
        }

        return R.ok("注册成功！");
    }

    @Override
    public R<String> logout(String token) {
        // 设置 token 到 Redis 黑名单，过期时间与 token 的有效期一致
        stringRedisTemplate.opsForValue().set("blacklist:" + token, "expired", JwtTokenUtils.EXPIRATION_TIME);
        return R.ok("登出成功！");
    }
}
