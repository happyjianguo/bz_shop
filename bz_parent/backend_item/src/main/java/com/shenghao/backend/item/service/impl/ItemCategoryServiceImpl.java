package com.shenghao.backend.item.service.impl;

import com.shenghao.backend.item.feign.CommonItemFeignClient;
import com.shenghao.backend.item.service.ItemCategoryService;
import com.shenghao.pojo.TbItemCat;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    /**
     * 根据类目id查询子类目
     * @param id
     * @return
     */
    @Override
    public Result selectItemCategoryByParentId(Long id) {
        List<TbItemCat> list = this.commonItemFeignClient.selectItemCategoryByParentId(id);
        if(null != list && list.size() > 0){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }
}
