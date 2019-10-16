package com.shenghao.common.redis.controller;

import com.shenghao.common.redis.service.ItemCategoryService;
import com.shenghao.utils.CatNode;
import com.shenghao.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存首页商品分类
 */
@RestController
@RequestMapping("/redis/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 向Redis中添加缓存数据
     */
    @RequestMapping("/insertItemCategory")
    public void insertItemCategory(@RequestBody CatResult catResult){
        this.itemCategoryService.insertItemCategory(catResult);
    }

    /**
     * 查询Redis中缓存的首页商品分类
     */
    @RequestMapping("/selectItemCategory")
    public CatResult selectItemCategory(){
        return this.itemCategoryService.selectItemCategory();
    }

}
