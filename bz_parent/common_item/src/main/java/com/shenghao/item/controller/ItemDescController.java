package com.shenghao.item.controller;

import com.shenghao.item.service.ItemDescService;
import com.shenghao.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * commonItem itemDescController
 */
@RestController
@RequestMapping("/service/itemDesc")
public class ItemDescController {

    @Autowired
    private ItemDescService itemDescService;

    /**
     * 添加商品描述
     * @param tbItemDesc
     * @return
     */
    @RequestMapping("/insertItemDesc")
    public Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc){
        return this.itemDescService.insertItemDesc(tbItemDesc);
    }

    /**
     * 更新商品描述
     */
    @RequestMapping("/updateItemDesc")
    public Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc){
        return this.itemDescService.updateItemDesc(tbItemDesc);
    }

    /**
     * 根据商品id查询商品描述
     */
    @RequestMapping("/selectItemDescByItemId")
    public TbItemDesc selectItemDescByItemId(@RequestParam Long itemId){
        return this.itemDescService.selectItemDescByItemId(itemId);
    }
}
