package com.shenghao.common.redis.service;

import com.shenghao.utils.CartItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface CartService {

    void insertCart(@RequestBody Map<String, Object> map);

    Map<String, CartItem> selectCartByUserId(String userId);
}
