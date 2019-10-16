package com.shenghao.common.redis.controller;

import com.shenghao.common.redis.service.ItemParamItemService;
import com.shenghao.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/itemParamItem")
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    /**
     * 缓存商品参数信息
     */
    @RequestMapping("/insertItemParamItem")
    public void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        this.itemParamItemService.insertItemParamItem(tbItemParamItem);
    }

    /**
     * 查询缓存商品参数信息
     */
    @RequestMapping("/selectItemParamItem")
    public TbItemParamItem selectItemParamItem(@RequestParam Long itemId){
        return this.itemParamItemService.selectItemParamItem(itemId);
    }

}
