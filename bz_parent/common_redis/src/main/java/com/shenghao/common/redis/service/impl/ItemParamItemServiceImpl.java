package com.shenghao.common.redis.service.impl;

import com.shenghao.common.redis.service.ItemParamItemService;
import com.shenghao.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${frontend_item_param_item_key}")
    private String frontend_item_param_item_key;

    @Override
    public void insertItemParamItem(TbItemParamItem tbItemParamItem) {
        this.redisTemplate.opsForValue().
                set(this.frontend_item_param_item_key + ":" + tbItemParamItem.getItemId(), tbItemParamItem);
    }

    @Override
    public TbItemParamItem selectItemParamItem(Long itemId) {
        return (TbItemParamItem) this.redisTemplate.opsForValue().
                get(this.frontend_item_param_item_key + ":" + itemId);
    }
}
