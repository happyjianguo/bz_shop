package com.shenghao.common.redis.service.impl;

import com.shenghao.common.redis.service.ItemCategoryService;
import com.shenghao.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 缓存首页商品分类业务层
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${frontend_catresult_redis_key}")
    private String frontend_catresult_redis_key;

    /**
     * 向缓存中添加首页商品分类
     * @param catResult
     */
    @Override
    public void insertItemCategory(CatResult catResult) {
        this.redisTemplate.opsForValue().set(this.frontend_catresult_redis_key, catResult);

    }

    /**
     * 查询缓存中的首页商品分类
     * @return
     */
    @Override
    public CatResult selectItemCategory() {

        return (CatResult) this.redisTemplate.opsForValue().get(this.frontend_catresult_redis_key);
    }
}
