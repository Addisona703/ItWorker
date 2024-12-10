package com.training.itworker.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义通过token注解，防止被拦截器拦截<br/>
 * <oi>
 *     <p>需要加PassToken的情况</p>
 *     <li>登录、注册、文件上传等</li>
 *     <li>不需要身份验证，任何用户可以直接访问</li>
 *     <p>不加PassToken的情况</p>
 *     <li>需要验证的接口，如查看个人信息、修改密码等</li>
 * </oi>
 * */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
