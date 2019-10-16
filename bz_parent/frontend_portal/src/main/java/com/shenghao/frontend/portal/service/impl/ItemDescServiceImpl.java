package com.shenghao.frontend.portal.service.impl;

import com.shenghao.frontend.portal.feign.CommonItemFeignClient;
import com.shenghao.frontend.portal.feign.CommonRedisFeignClient;
import com.shenghao.frontend.portal.service.ItemDescService;
import com.shenghao.pojo.TbItemDesc;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Override
    public Result selectItemDescByItemId(Long itemId) {
        //查询缓存
        try{
            TbItemDesc tbItemDesc = this.commonRedisFeignClient.selectItemDesc(itemId);
            if(tbItemDesc != null){
                return Result.ok(tbItemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        TbItemDesc tbItemDesc = this.commonItemFeignClient.selectItemDescByItemId(itemId);
        //加入缓存
        try{
            if(tbItemDesc != null){
                this.commonRedisFeignClient.insertItemDesc(tbItemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回
        if(tbItemDesc != null){
            return Result.ok(tbItemDesc);
        }
        return Result.error("查无结果");
    }
}
