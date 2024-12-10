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
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/v3/api-docs/**",      // 放行 OpenAPI 数据
                        "/swagger-ui/**",       // 放行 Swagger 静态资源
                        "/swagger-ui.html",     // 放行 Swagger UI 页面
                        "/favicon.ico"          // 可选，放行 Swagger 默认的图标
                );
    }
}
