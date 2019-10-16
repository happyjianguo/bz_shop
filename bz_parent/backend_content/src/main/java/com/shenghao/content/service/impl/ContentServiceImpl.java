package com.shenghao.content.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.shenghao.content.feign.CommonContentFeignClient;
import com.shenghao.content.service.ContentService;
import com.shenghao.pojo.TbContent;
import com.shenghao.utils.PageResult;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    /**
     * 根据分类id查询分类内容
     * @param page
     * @param rows
     * @param categoryId
     * @return
     */
    @Override
    public Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageResult pageResult = this.commonContentFeignClient.selectTbContentAllByCategoryId(page, rows, categoryId);
        if(null != pageResult && pageResult.getResult().size() > 0){
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    /**
     * 根据分类添加内容
     * @param tbContent
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertTbContent(TbContent tbContent) {
        Integer num = this.commonContentFeignClient.insertTbContent(tbContent);
        if(num != null){
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    @Override
    public Result deleteContentByIds(Long ids) {
        Integer num = this.commonContentFeignClient.deleteContentByIds(ids);
        if(num != null){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

}
