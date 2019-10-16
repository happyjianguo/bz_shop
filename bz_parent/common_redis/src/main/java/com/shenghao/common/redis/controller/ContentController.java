package com.shenghao.common.redis.controller;

import com.shenghao.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 缓存首页大广告位
 */
@RestController
@RequestMapping("/redis/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 将大广告位的数据添加到缓存中
     */
    @RequestMapping("/insertContentAD")
    public void insertContentAD(@RequestBody List<Map> list){
        this.contentService.insertContentAD(list);
    }

    /**
     * 查询缓存中首页大广告位的数据
     */
    @RequestMapping("/selectContentAD")
    public List<Map> selectContentAD(){
        return this.contentService.selectContentAD();
    }
}
