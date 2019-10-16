package com.shenghao.common.redis.service.impl;

import com.shenghao.common.redis.service.CartService;
import com.shenghao.utils.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 购物车操作业务层
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${frontend_cart_redis_key}")
    private String frontend_cart_redis_key;

    /**
     * 缓存购物车
     * @param map
     */
    @Override
    public void insertCart(Map<String, Object> map) {
        String userId = (String) map.get("userId");
        Map<String, CartItem> cart = (Map<String, CartItem>) map.get("cart");
        this.redisTemplate.opsForHash().put(this.frontend_cart_redis_key, userId, cart);
    }

    /**
     * 根据用户id查询用户购物车
     * @param userId
     * @return
     */
    @Override
    public Map<String, CartItem> selectCartByUserId(String userId) {
        return (Map<String, CartItem>) this.redisTemplate.opsForHash().get(this.frontend_cart_redis_key, userId);
    }
}
