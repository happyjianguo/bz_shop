package com.shenghao.backend.item.controller;

import com.shenghao.backend.item.service.ItemService;
import com.shenghao.pojo.TbItem;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/backend/item")
@RefreshScope   //对注入的值做刷新需要这个注解
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Value("${test}")
    private String msg;

    /**
     * 查询商品并分页处理
     * @return
     */
    @RequestMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "30") Integer rows){
        try{
            //测试自动刷新配置信息
//            System.out.println(this.msg);

            return this.itemService.selectTbItemAllByPage(page, rows);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 添加商品
     */
    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams){
        try{
            return this.itemService.insertTbItem(tbItem, desc, itemParams);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 删除商品
     */
    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Long itemId){
        try{
            return this.itemService.deleteItemById(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");

    }

    /**
     * 预更新商品查询
     */
    @RequestMapping("/preUpdateItem")
    public Result preUpdateItem(Long itemId){
        try{
            return this.itemService.preUpdateItem(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 商品更新
     */
    @RequestMapping("/updateTbItem")
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams){
        try{
            return this.itemService.updateTbItem(tbItem, desc, itemParams);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");

    }
}
