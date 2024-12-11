package com.training.itworker.config;

import com.training.itworker.Interceptor.JwtInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  网络配置
 * */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    /** 处理跨域请求 **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5173")
                .allowCredentials(true)
                .maxAge(16800)
                .allowedHeaders("*")
                .allowedMethods("*");
    }

    /**
     * 拦截所有请求验证Token
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns(
                        // OpenAPI 文档和 Swagger UI 相关资源
                        "/docs/index.html",
                        "/docs/api/**",
                        "/favicon.ico",
                        // 静态资源目录放行（如有）
                        "/static/**",
                        "/public/**"
                );
    }

}
