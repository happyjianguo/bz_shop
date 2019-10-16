package com.shenghao.frontend.portal.feign;

import com.shenghao.pojo.TbItem;
import com.shenghao.pojo.TbItemDesc;
import com.shenghao.pojo.TbItemParamItem;
import com.shenghao.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "common-redis")
public interface CommonRedisFeignClient {
    //------/redis/itemCategory
    @PostMapping("/redis/itemCategory/insertItemCategory")
    void insertItemCategory(@RequestBody CatResult catResult);

    @PostMapping("/redis/itemCategory/selectItemCategory")
    CatResult selectItemCategory();

    //------/redis/content
    @PostMapping("/redis/content/insertContentAD")
    void insertContentAD(@RequestBody List<Map> list);

    @PostMapping("/redis/content/selectContentAD")
    List<Map> selectContentAD();

    //------/redis/item
    @PostMapping("/redis/item/insertItemBasicInfo")
    void insertItemBasicInfo(@RequestBody TbItem tbItem);

    @PostMapping("/redis/item/selectItemBasicInfo")
    TbItem selectItemBasicInfo(@RequestParam("tbItemId") Long tbItemId);

    //------/redis/itemDesc
    @PostMapping("/redis/itemDesc/selectItemDesc")
    TbItemDesc selectItemDesc(@RequestParam("itemId") Long itemId);

    @PostMapping("/redis/itemDesc/insertItemDesc")
    void insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    //------/redis/itemParamItem
    @PostMapping("/redis/itemParamItem/insertItemParamItem")
    void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

    @PostMapping("/redis/itemParamItem/selectItemParamItem")
    TbItemParamItem selectItemParamItem(@RequestParam("itemId") Long itemId);
}
