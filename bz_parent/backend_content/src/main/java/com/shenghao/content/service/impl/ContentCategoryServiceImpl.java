package com.shenghao.content.service.impl;

import com.shenghao.content.feign.CommonContentFeignClient;
import com.shenghao.content.service.ContentCategoryService;
import com.shenghao.pojo.TbContentCategory;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    /**
     * 根据id查询内容分类
     * @param id
     * @return
     */
    @Override
    public Result selectContentCategoryByParentId(Long id) {
        List<TbContentCategory> list = this.commonContentFeignClient.selectContentCategoryByParentId(id);
        if(null != list && list.size() > 0){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }

    /**
     * 添加内容分类
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public Result insertContentCategory(Long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        //由于数据已经在common-content层补齐，直接调用即可
        Integer contentCategoryNum = this.commonContentFeignClient.insertContentCategory(tbContentCategory);
        if(null != contentCategoryNum){
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    @Override
    public Result deleteContentCategoryById(Long categoryId) {
        Integer contentCategoryNum = this.commonContentFeignClient.deleteContentCategoryById(categoryId);
        if(contentCategoryNum == 200){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    @Override
    public Result updateContentCategory(Long id, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(id);
        tbContentCategory.setName(name);
        Integer status = this.commonContentFeignClient.updateContentCategory(tbContentCategory);
        if(null != status){
            return Result.ok();
        }
        return Result.error("更新失败");
    }
}
