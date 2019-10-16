package com.shenghao.frontend.portal.feign;

import com.shenghao.pojo.TbItem;
import com.shenghao.pojo.TbItemDesc;
import com.shenghao.pojo.TbItemParamItem;
import com.shenghao.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-item")
public interface CommonItemFeignClient {

    //------/service/itemCategory
    @PostMapping("/service/itemCategory/selectItemCategoryAll")
    CatResult selectItemCategoryAll();

    //------/service/item
    @PostMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId") Long itemId);

    //------/service/itemDesc
    @PostMapping("/service/itemDesc/selectItemDescByItemId")
    TbItemDesc selectItemDescByItemId(@RequestParam("itemId") Long itemId);

    //------/service/itemParamItem
    @PostMapping("/service/itemParamItem/selectTbItemParamItemByItemId")
    TbItemParamItem selectTbItemParamItemByItemId(@RequestParam("itemId") Long itemId);
}
