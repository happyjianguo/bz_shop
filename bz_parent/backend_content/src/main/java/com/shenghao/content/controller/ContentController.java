package com.shenghao.content.controller;

import com.shenghao.content.service.ContentService;
import com.shenghao.pojo.TbContent;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 根据分类id查询内容
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "30") Integer rows,
                                                 Long categoryId){
        try{
            return this.contentService.selectTbContentAllByCategoryId(page, rows, categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 根据分类添加内容
     */
    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent){
        try{
            return this.contentService.insertTbContent(tbContent);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 根据id删除内容
     */
    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids){
        try{
            return this.contentService.deleteContentByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");

    }


}
