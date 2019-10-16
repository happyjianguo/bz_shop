package com.shenghao.content.controller;

import com.shenghao.content.service.ContentService;
import com.shenghao.pojo.TbContent;
import com.shenghao.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 根据内容分类id查询内容
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(@RequestParam Integer page,
                                                     @RequestParam Integer rows,
                                                     @RequestParam Long categoryId){
        return this.contentService.selectTbContentAllByCategoryId(page, rows, categoryId);
    }

    /**
     * 根据分类添加内容
     */
    @RequestMapping("/insertTbContent")
    public Integer insertTbContent(@RequestBody TbContent tbContent){
        return this.contentService.insertTbContent(tbContent);
    }

    /**
     * 根据内容id删除内容
     */
    @RequestMapping("/deleteContentByIds")
    public Integer deleteContentByIds(@RequestParam Long id){
        return this.contentService.deleteContentByIds(id);
    }

    /**
     * 查询首页大广告位
     */
    @RequestMapping("/selectFrontendContentByAD")
    public List<Map> selectFrontendContentByAD(){
        return this.contentService.selectFrontendContentByAD();
    }
}
