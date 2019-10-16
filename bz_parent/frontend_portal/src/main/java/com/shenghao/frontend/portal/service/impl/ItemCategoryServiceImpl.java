package com.shenghao.frontend.portal.service.impl;

import com.shenghao.frontend.portal.feign.CommonItemFeignClient;
import com.shenghao.frontend.portal.feign.CommonRedisFeignClient;
import com.shenghao.frontend.portal.service.ItemCategoryService;
import com.shenghao.utils.CatResult;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * 商品分类业务层
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    /**
     * 查询首页商品分类
     * @return
     */
    @Override
    public Result selectItemCategoryAll() {
        //查询缓存
        try {
            CatResult catResult = this.commonRedisFeignClient.selectItemCategory();
            //判断缓存是否命中
            if(catResult != null && catResult.getData() != null && catResult.getData().size() > 0){
                return Result.ok(catResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        CatResult catResult = this.commonItemFeignClient.selectItemCategoryAll();

        //添加到缓存
        try{
            if(catResult != null && catResult.getData() != null && catResult.getData().size() > 0){
                this.commonRedisFeignClient.insertItemCategory(catResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //返回结果
        if(catResult != null && catResult.getData() != null && catResult.getData().size() > 0){
            return Result.ok(catResult);
        }
        return Result.error("查无结果");
    }
}
