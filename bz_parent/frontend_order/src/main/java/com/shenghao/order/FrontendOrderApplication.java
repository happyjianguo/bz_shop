package com.shenghao.order;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单服务
 */
@SpringBootApplication
@EnableDiscoveryClient//Eureka和Zookeeper都可以
@EnableDistributedTransaction
@MapperScan("com.shenghao.mapper")
@EnableFeignClients
public class FrontendOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendOrderApplication.class, args);
    }

}
