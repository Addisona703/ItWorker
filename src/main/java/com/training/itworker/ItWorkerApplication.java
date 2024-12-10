package com.training.itworker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.training.itworker.mapper")
public class ItWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItWorkerApplication.class, args);
    }

}
