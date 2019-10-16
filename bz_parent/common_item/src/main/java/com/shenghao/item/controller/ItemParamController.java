package com.shenghao.item.controller;

import com.shenghao.item.service.ItemParamService;
import com.shenghao.pojo.TbItemParam;
import com.shenghao.utils.PageResult;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品参数
 */
@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据商品分类id查询规格参数模板
     */
    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(@RequestParam Long itemCatId){
        return this.itemParamService.selectItemParamByItemCatId(itemCatId);
    }

    /**
     * 查询所有商品规格参数模板
     */
    @RequestMapping("/selectItemParamAll")
    public PageResult selectItemParamAll(@RequestParam Integer page, @RequestParam Integer rows){
        return this.itemParamService.selectItemParamAll(page, rows);
    }

    /**
     * 根据商品分类添加规格参数模板
     */
    @RequestMapping("/insertItemParam")
    public Integer insertItemParam(@RequestBody TbItemParam tbItemParam){
        return this.itemParamService.insertItemParam(tbItemParam);
    }

    /**
     * 根据商品分类参数模板ID删除对应参数模板
     */
    @RequestMapping("/deleteItemParamById")
    public Integer deleteItemParamById(@RequestParam Long id){
        return this.itemParamService.deleteItemParamById(id);
    }
}
