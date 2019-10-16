package com.shenghao.order.feign;

import com.shenghao.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FeignClient(value = "frontend-cart")
public interface FrontendCartFeignClient {
    //------/cart
    @PostMapping("/cart/deleteItemFromCart")
    Result deleteItemFromCart(@RequestParam("itemId") Long itemId, @RequestParam("userId") String userId);


}
