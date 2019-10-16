package com.shenghao.backend.item.controller;

import com.shenghao.backend.item.service.ItemParamService;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/itemParam")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据商品分类id查询规格参数模板
     */
    @RequestMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable Long itemCatId){
        try{
            return this.itemParamService.selectItemParamByItemCatId(itemCatId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 查询全部商品规格参数
     */
    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "30") Integer rows){
        try{
            return this.itemParamService.selectItemParamAll(page, rows);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 添加商品分类规格参数模板
     */
    @RequestMapping("/insertItemParam")
    public Result insertItemParam(Long itemCatId, String paramData){
        try{
            return this.itemParamService.insertItemParam(itemCatId, paramData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 根据id删除商品分类参数模板
     */
    @RequestMapping("/deleteItemParamById")
    public Result deleteItemParamById(Long id){
        try{
            return this.itemParamService.deleteItemParamById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");

    }
}
