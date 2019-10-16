package com.shenghao.search.service.impl;

import com.shenghao.mapper.SolrItemMapper;
import com.shenghao.pojo.SolrItem;
import com.shenghao.search.service.SolrService;
import com.shenghao.utils.Result;
import com.shenghao.utils.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import javax.swing.text.Highlighter;
import java.util.List;

/**
 * 搜索服务业务层
 */
@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    private SolrItemMapper solrItemMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    @Value("${spring.data.solr.core}")
    private String collection;

    /**
     * 向索引库中导入数据
     * @return
     */
    @Override
    public Result importAll() {
        try{
            //查询数据
            List<SolrItem> list = this.solrItemMapper.getItemList();

            //将数据添加到索引库中
            for(SolrItem item : list){
                //创建SolrInputDocument对象
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSell_point());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getName());
                document.setField("item_desc", item.getItem_desc());

                //写入索引库
                this.solrTemplate.saveDocument(this.collection, document);
            }
            //提交事务
            this.solrTemplate.commit(this.collection);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("导入失败");
    }

    /**
     * 搜索solr索引库
     * @param q
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<SolrDocument> selectByq(String q, Long page, Integer pageSize) {

        //设置高亮查询条件
        HighlightQuery query = new SimpleHighlightQuery();
        Criteria criteria = new Criteria("item_keywords");//对复合域进行查询
        criteria.is(q);
        query.addCriteria(criteria);

        //设置高亮属性
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.addField("item_title");//设置高亮显示的域
        highlightOptions.setSimplePrefix("<em style='color:red'>");//设置高亮样式的前缀
        highlightOptions.setSimplePostfix("</em>");
        query.setHighlightOptions(highlightOptions);

        //分页
        query.setOffset((page - 1) * pageSize);
        query.setRows(pageSize);

        //使用springboot data solr的好处就是它能帮我们封装好数据模型，不用我们手动封装
        HighlightPage<SolrDocument> highlightPage = this.solrTemplate.
                queryForHighlightPage(this.collection, query, SolrDocument.class);

        List<HighlightEntry<SolrDocument>> highlighted = highlightPage.getHighlighted();
        for(HighlightEntry<SolrDocument> tbItemHighlightEntry : highlighted){
            SolrDocument entity = tbItemHighlightEntry.getEntity();//原始的实体对象，没有高亮
            List<HighlightEntry.Highlight> highlights = tbItemHighlightEntry.getHighlights();
            //如果有高亮，就取高亮
            if(highlights != null && highlights.size() > 0 && highlights.get(0).getSnipplets().size() > 0){
                entity.setItem_title(highlights.get(0).getSnipplets().get(0));
            }
        }
        List<SolrDocument> list = highlightPage.getContent();
        return list;
    }
}
