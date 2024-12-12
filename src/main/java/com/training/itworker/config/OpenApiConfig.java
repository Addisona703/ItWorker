package com.training.itworker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("个人博客应用 API")
                        .version("1.0.0")
                        .description("Java Web实训")
                        .license(new License().name("Apache 2.0").url("""
                                https://example.com""")) // 设置 API 的许可证信息，包括许可证名称和 URL
                        .contact(new Contact()
                                .name("KEYKB")
                                .url("https://github.com/Addisona703/ItWorker")
                                .email("3183389935@qq.com")
                        )
                );
    }
}
