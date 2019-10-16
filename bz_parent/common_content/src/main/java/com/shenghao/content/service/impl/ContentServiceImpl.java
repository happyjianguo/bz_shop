package com.shenghao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenghao.content.service.ContentService;
import com.shenghao.mapper.TbContentMapper;
import com.shenghao.pojo.TbContent;
import com.shenghao.pojo.TbContentExample;
import com.shenghao.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Value("${frontend.AD}")
    private Long AD;

    @Override
    public PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = this.tbContentMapper.selectByExample(example);

        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        PageResult result = new PageResult();
        result.setPageIndex(page);
        result.setTotalPage(pageInfo.getTotal());
        result.setResult(pageInfo.getList());
        return result;
    }

    @Override
    public Integer insertTbContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        return this.tbContentMapper.insertSelective(tbContent);
    }

    @Override
    public Integer deleteContentByIds(Long id) {
        return this.tbContentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询首页大广告位
     * @return
     */
    @Override
    public List<Map> selectFrontendContentByAD() {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(this.AD);
        List<TbContent> list = this.tbContentMapper.selectByExample(example);
        List<Map> result = new ArrayList<>();
        //模型转换
        for(TbContent content : list){
            Map map = new HashMap();
            map.put("heightB", 240);
            map.put("src", content.getPic());
            map.put("width", 670);
            map.put("alt", content.getSubTitle());
            map.put("srcB", null);
            map.put("widthB", 550);
            map.put("href", content.getUrl());
            map.put("height", 240);
            result.add(map);
        }
        return result;
    }
}
