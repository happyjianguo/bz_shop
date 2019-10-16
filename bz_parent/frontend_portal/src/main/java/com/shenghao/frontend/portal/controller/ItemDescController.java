package com.shenghao.frontend.portal.controller;

import com.shenghao.frontend.portal.service.ItemDescService;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/item")
public class ItemDescController {

    @Autowired
    private ItemDescService itemDescService;

    /**
     * 根据商品id查询商品描述
     */
    @RequestMapping("/selectItemDescByItemId")
    public Result selectItemDescByItemId(Long itemId){
        try{
            return this.itemDescService.selectItemDescByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
