package com.shenghao.frontend.portal.controller;

import com.shenghao.frontend.portal.service.ItemParamItemService;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/item")
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/selectTbItemParamItemByItemId")
    public Result selectTbItemParamItemByItemId(Long itemId){
        try {
            return this.itemParamItemService.selectTbItemParamItemByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
