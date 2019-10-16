package com.shenghao.search.controller;

import com.shenghao.search.service.SolrService;
import com.shenghao.utils.Result;
import com.shenghao.utils.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 搜索服务Controller
 */
@RestController
@RequestMapping("/search")
public class SolrController {

    @Autowired
    private SolrService solrService;

    /**
     * 向Solr索引库中导入数据
     */
    @RequestMapping("/importAll")
    public Result importAll(){
        return this.solrService.importAll();
    }

    /**
     * 搜索数据
     */
    @RequestMapping("/list")
    public List<SolrDocument> selectByq(String q,
                                        @RequestParam(defaultValue = "1") Long page,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        try{
            return this.solrService.selectByq(q, page, pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
