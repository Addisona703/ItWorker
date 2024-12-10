package com.training.itworker.Interceptor;

import cn.hutool.core.text.CharSequenceUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.training.itworker.common.MyException;
import com.training.itworker.common.PassToken;
import com.training.itworker.common.ResponseEnum;
import com.training.itworker.entity.User;
import com.training.itworker.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

/** 拦截器，用于处理请求到达控制器之前、后或者视图渲染后进行拦截 **/

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private UserServiceImpl userService;

    /**
     * 处理器的预处理回调
     *
     * <p>处理流程：
     * <ol>
     *     <li>从请求头中取出Token</li>
     *     <li>判断是否映射到方法</li>
     *     <li>检查是否有PassToken注释, 有则跳过认证</li>
     *     <li>检查有无需要用户登录的注解，有则需要取出验证</li>
     *     <li>认证通过则可以访问，不通过显示错误信息</li>
     * </ol>
     *
     * @param handler 响应的处理器，返回true表示流程继续，返回false表示流程中断
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(!(handler instanceof HandlerMethod handlerMethod)){
            return true;
        }
        Method method = handlerMethod.getMethod();
        // 检查是否通过，有passToken注解
        if(method.isAnnotationPresent(PassToken.class)) {
            // 有就跳过认证检查
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()) {
                return true;
            }
        }

        // 查看是否存有这个token
        if(CharSequenceUtil.isBlank(token)) {
            throw new MyException(ResponseEnum.TOKEN_EX.getCode(), ResponseEnum.TOKEN_EX.getMsg());
        }

        // 获取Id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new MyException(ResponseEnum.TOKEN_EX.getCode(), ResponseEnum.TOKEN_EX.getMsg());
        }

        // 根据Token中的userId查询
        User user = userService.getById(userId);
        if(user == null) {
            throw new MyException(ResponseEnum.TOKEN_EX.getCode(), ResponseEnum.TOKEN_EX.getMsg());
        }

        // 验证Token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword()))
                // 容忍token过期时间，类似与超时后能允许在超会
                                     .acceptExpiresAt(0)
                                     .build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new MyException(406, "权限认证失败!");
        }
        return true;
    }

    /**
     * 后处理回调
     * @Method void postHandle()
     *
     * 请求处理完毕的回调，当前对应的preHendle()返回值为true时执行，一般用于资源清理
     * @Method void afterCompletion()
     */

}
