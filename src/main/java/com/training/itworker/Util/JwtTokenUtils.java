package com.training.itworker.Util;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/** 生成Token **/

public class JwtTokenUtils {

    // 签名密钥
    private static final String SECRET_KEY = "123456";

    // Token 有效期
    private static final long EXPIRATION_TIME = 2 * 24 * 60 * 60 * 1000;

    /** 定义私有的构造函数方式外部创建JwtTokenUtils的实例 **/
    private JwtTokenUtils() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * 生成token
     * @param userId : 用户id
     * @param sign : 密码
     * @return String
     */
    public static String getToken(String userId) {
        return JWT.create()
                // 存入需要保存在Token的信息
                .withAudience(userId)
                // 截止时间
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                // 密钥签名，这里使用hash256算法
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
}
