package com.shenghao.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 搜索服务启动类
 */
@SpringBootApplication
@MapperScan("com.shenghao.mapper")

public class FrontendSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendSearchApplication.class, args);
    }
}
