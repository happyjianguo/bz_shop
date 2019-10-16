package com.shenghao.content.controller;

import com.shenghao.content.service.ContentCategoryService;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 根据当前节点id查询子节点
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(@RequestParam(defaultValue = "0") Long id){
        try{
            return this.contentCategoryService.selectContentCategoryByParentId(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 添加内容分类
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping("/insertContentCategory")
    public Result insertContentCategory(Long parentId, String name){
        try{
            return this.contentCategoryService.insertContentCategory(parentId, name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 删除内容分类
     */
    @RequestMapping("/deleteContentCategoryById")
    public Result deleteContentCategoryById(Long categoryId){
        try{
            return this.contentCategoryService.deleteContentCategoryById(categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 修改内容分类
     */
    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(Long id, String name){
        try{
            return this.contentCategoryService.updateContentCategory(id, name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
