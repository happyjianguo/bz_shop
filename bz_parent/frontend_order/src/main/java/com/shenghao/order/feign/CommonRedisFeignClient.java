package com.shenghao.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "common-redis")
public interface CommonRedisFeignClient {
    //------/redis/order
    @PostMapping("/redis/order/selectOrderId")
    Long selectOrderId();
}
