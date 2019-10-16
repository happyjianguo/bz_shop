package com.shenghao.frontend.portal.controller;

import com.shenghao.frontend.portal.service.ContentService;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页内容管理
 */
@RestController
@RequestMapping("/frontend/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 查询首页大广告位
     */
    @RequestMapping("/selectFrontendContentByAD")
    public Result selectFrontendContentByAD(){
        try{
            //测试服务降级
//            Thread.sleep(10000);
            return contentService.selectFrontendContentByAD();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
