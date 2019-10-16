package com.shenghao.sso;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient//Eureka和Zookeeper都可以
@EnableDistributedTransaction
@MapperScan("com.shenghao.mapper")
@EnableFeignClients
public class FrontendSSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendSSOApplication.class, args);
    }
}
