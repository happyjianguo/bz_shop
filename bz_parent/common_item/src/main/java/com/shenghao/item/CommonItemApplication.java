package com.shenghao.item;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 通用服务Common_item
 */
@SpringBootApplication
//@EnableEurekaClient//只会到Eureka注册中心注册
@EnableDiscoveryClient//Eureka和Zookeeper都可以
@EnableDistributedTransaction
@MapperScan("com.shenghao.mapper")
public class CommonItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonItemApplication.class);
    }
}
