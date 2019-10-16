package com.shenghao.common.redis.service.impl;

import com.shenghao.common.redis.service.ItemDescService;
import com.shenghao.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${frontend_item_desc_key}")
    private String frontend_item_desc_key;

    /**
     * 缓存商品描述信息
     * @param tbItemDesc
     */
    @Override
    public void insertItemDesc(TbItemDesc tbItemDesc) {
        this.redisTemplate.opsForValue().set(frontend_item_desc_key + ":" + tbItemDesc.getItemId(), tbItemDesc);
    }

    /**
     * 查询缓存商品描述信息
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc selectItemDesc(Long itemId) {
        return (TbItemDesc) this.redisTemplate.opsForValue().get(frontend_item_desc_key + ":" + itemId);
    }

}
