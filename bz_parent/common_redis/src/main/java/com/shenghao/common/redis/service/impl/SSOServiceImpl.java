package com.shenghao.common.redis.service.impl;

import com.shenghao.common.redis.service.SSOService;
import com.shenghao.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存用户业务层
 */
@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${user_session_redis_key}")
    private String user_session_redis_key;

    @Override
    public void insertUser(TbUser user, String userToken) {
        user.setPassword("");//将密码置空
        this.redisTemplate.opsForValue().set(this.user_session_redis_key + ":" + userToken, user, 1, TimeUnit.DAYS);
    }

    /**
     * 用户退出登陆
     * @param userToken
     */
    @Override
    public void logOut(String userToken) {
        this.redisTemplate.delete(this.user_session_redis_key + ":" + userToken);
    }

    /**
     * 根据用户token判断用户在redis中是否失效
     * @param token
     * @return
     */
    @Override
    public TbUser checkUserToken(String token) {
        return (TbUser) this.redisTemplate.opsForValue().get(this.user_session_redis_key + ":" + token);
    }
}
