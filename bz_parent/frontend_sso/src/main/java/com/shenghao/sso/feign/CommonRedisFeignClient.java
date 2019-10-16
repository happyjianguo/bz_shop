package com.shenghao.sso.feign;

import com.shenghao.pojo.TbUser;
import com.shenghao.utils.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "common-redis")
public interface CommonRedisFeignClient {

    //------/sso/redis
    @PostMapping("/sso/redis/insertUser")
    void insertUser(@RequestBody TbUser user, @RequestParam("userToken") String userToken);

    @PostMapping("/sso/redis/logOut")
    void logOut(@RequestParam("userToken") String userToken);

    //------/redis/cart
    @PostMapping("/redis/cart/insertCart")
    void insertCart(@RequestBody Map<String, Object> map);

    @PostMapping("/redis/cart/selectCartByUserId")
    Map<String, CartItem> selectCartByUserId(@RequestParam("userId") String userId);
}
