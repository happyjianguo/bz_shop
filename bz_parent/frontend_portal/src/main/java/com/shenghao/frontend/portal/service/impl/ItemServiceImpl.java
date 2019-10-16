package com.shenghao.frontend.portal.service.impl;

import com.shenghao.frontend.portal.feign.CommonItemFeignClient;
import com.shenghao.frontend.portal.feign.CommonRedisFeignClient;
import com.shenghao.frontend.portal.service.ItemService;
import com.shenghao.pojo.TbItem;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    @Override
    public Result selectItemInfo(Long itemId) {
        //查询缓存
        try{
            TbItem tbItem = this.commonRedisFeignClient.selectItemBasicInfo(itemId);
            if(tbItem != null){
                return Result.ok(tbItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        TbItem tbItem = this.commonItemFeignClient.selectItemInfo(itemId);

        //加到缓存中
        try{
            if(tbItem != null){
                this.commonRedisFeignClient.insertItemBasicInfo(tbItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回
        if(tbItem != null){
            return Result.ok(tbItem);
        }
        return Result.error("查无结果");
    }
}
