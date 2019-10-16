package com.shenghao.common.redis.service.impl;

import com.shenghao.common.redis.service.ItemService;
import com.shenghao.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Value("${frontend_item_basic_info_key}")
    private String frontend_item_basic_info_key;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存商品基本信息
     * @param tbItem
     */
    @Override
    public void insertItemBasicInfo(TbItem tbItem) {
        this.redisTemplate.opsForValue().set(this.frontend_item_basic_info_key + ":" + tbItem.getId(), tbItem);
    }

    /**
     * 查询缓存商品基本信息
     * @param tbItemId
     * @return
     */
    @Override
    public TbItem selectItemBasicInfo(Long tbItemId) {
        return (TbItem) this.redisTemplate.opsForValue().get(this.frontend_item_basic_info_key + ":" + tbItemId);
    }
}
