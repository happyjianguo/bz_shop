package com.shenghao.content;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * CommonContent启动类
 */
@SpringBootApplication
@EnableDiscoveryClient//Eureka和Zookeeper都可以
@EnableDistributedTransaction
@MapperScan("com.shenghao.mapper")
public class CommonContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonContentApplication.class);
    }
}
