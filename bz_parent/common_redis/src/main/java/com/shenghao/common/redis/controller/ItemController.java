package com.shenghao.common.redis.controller;

import com.shenghao.common.redis.service.ItemService;
import com.shenghao.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存商品信息
 */
@RestController
@RequestMapping("/redis/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 缓存商品基本信息
     */
    @RequestMapping("/insertItemBasicInfo")
    public void insertItemBasicInfo(@RequestBody TbItem tbItem){
        this.itemService.insertItemBasicInfo(tbItem);
    }

    /**
     * 查询缓存中的商品基本信息
     */
    @RequestMapping("/selectItemBasicInfo")
    public TbItem selectItemBasicInfo(@RequestParam Long tbItemId){
        return this.itemService.selectItemBasicInfo(tbItemId);
    }
}
