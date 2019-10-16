package com.shenghao.backend.item.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.shenghao.backend.item.feign.CommonItemFeignClient;
import com.shenghao.backend.item.service.ItemParamService;
import com.shenghao.pojo.TbItemParam;
import com.shenghao.utils.PageResult;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Override
    public Result selectItemParamByItemCatId(Long itemCatId) {
        TbItemParam tbItemParam = this.commonItemFeignClient.selectItemParamByItemCatId(itemCatId);
        if(null != tbItemParam){
            return Result.ok(tbItemParam);
        }
        return Result.error("查无结果");
    }

    /**
     * 查询所有规格参数模板
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result selectItemParamAll(Integer page, Integer rows) {
        PageResult pageResult = this.commonItemFeignClient.selectItemParamAll(page, rows);
        if(null != pageResult && pageResult.getResult().size() > 0){
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    /**
     * 添加商品分类规格参数模板
     * @param itemCatId
     * @param paramData
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertItemParam(Long itemCatId, String paramData) {
        //创建TbItemParam对象并补齐数据
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(itemCatId);
        tbItemParam.setParamData(paramData);
        Date date = new Date();
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);

        Integer itemParamNum = this.commonItemFeignClient.insertItemParam(tbItemParam);
        if(null != itemParamNum){
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    @Override
    public Result deleteItemParamById(Long id) {
        Integer itemParamNum = this.commonItemFeignClient.deleteItemParamById(id);
        if(null != itemParamNum){
            return Result.ok();
        }
        return Result.error("删除失败");
    }
}
