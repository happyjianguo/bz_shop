package com.shenghao.frontend.portal.service.impl;

import com.shenghao.frontend.portal.feign.CommonItemFeignClient;
import com.shenghao.frontend.portal.feign.CommonRedisFeignClient;
import com.shenghao.frontend.portal.service.ItemParamItemService;
import com.shenghao.pojo.TbItemParamItem;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    /**
     * 根据商品id查询其规格参数
     * @param itemId
     * @return
     */
    @Override
    public Result selectTbItemParamItemByItemId(Long itemId) {
        //查询缓存
        try{
            TbItemParamItem tbItemParamItem = this.commonRedisFeignClient.selectItemParamItem(itemId);
            if(tbItemParamItem != null){
                return Result.ok(tbItemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        TbItemParamItem tbItemParamItem = this.commonItemFeignClient.selectTbItemParamItemByItemId(itemId);
        //添加缓存
        try{
            if(tbItemParamItem != null){
                this.commonRedisFeignClient.insertItemParamItem(tbItemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回
        if(tbItemParamItem != null){
            return Result.ok(tbItemParamItem);
        }
        return Result.error("查无结果");
    }
}
