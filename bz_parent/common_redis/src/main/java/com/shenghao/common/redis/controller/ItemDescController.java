package com.shenghao.common.redis.controller;

import com.shenghao.common.redis.service.ItemDescService;
import com.shenghao.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/itemDesc")
public class ItemDescController {

    @Autowired
    private ItemDescService itemDescService;

    /**
     * 缓存商品描述信息
     */
    @RequestMapping("/insertItemDesc")
    public void insertItemDesc(@RequestBody TbItemDesc tbItemDesc){
        this.itemDescService.insertItemDesc(tbItemDesc);
    }

    /**
     * 查询缓存商品描述信息
     */
    @RequestMapping("/selectItemDesc")
    public TbItemDesc selectItemDesc(@RequestParam Long itemId){
        return this.itemDescService.selectItemDesc(itemId);
    }
}
