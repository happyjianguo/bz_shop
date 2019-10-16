package com.shenghao.item.controller;

import com.shenghao.item.service.ItemService;
import com.shenghao.pojo.TbItem;
import com.shenghao.utils.PageResult;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/service/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品数据
     */
    @RequestMapping(value = "/selectTbItemAllByPage", method = RequestMethod.GET)
    public PageResult selectTbItemAllByPage(@RequestParam Integer page, @RequestParam Integer rows){
        return this.itemService.selectTbItemAllByPage(page, rows);
    }

    /**
     * 商品的添加
     */
    @RequestMapping("/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem){
        return this.itemService.insertTbItem(tbItem);
    }

    /**
     * 删除商品
     * @param tbItem
     * @return
     */
    @RequestMapping("/deleteItemById")
    public Integer deleteItemById(@RequestBody TbItem tbItem){
        return this.itemService.updateItemById(tbItem);
    }

    /**
     * 根据商品ID查询商品，商品分类，商品描述，商品规格参数
     */
    @RequestMapping("/preUpdateItem")
    public Map<String, Object> preUpdateItem(@RequestParam Long itemId){
        return this.itemService.preUpdateItem(itemId);
    }

    /**
     * 对商品表的更新操作
     * 更新的时候分别对商品表，描述表和类目表更新有以下两种原因：
     * 1. RequestBody只能有一个
     * 2. 就算RequestBody传一个Map集合，在同一个方法中操作三张表会失去原子性
     */
    @RequestMapping("/updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem){
        return this.itemService.updateItemById(tbItem);
    }

    /**
     * 根据商品id查询商品
     */
    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(@RequestParam Long itemId){
        return this.itemService.selectItemInfo(itemId);
    }


}
