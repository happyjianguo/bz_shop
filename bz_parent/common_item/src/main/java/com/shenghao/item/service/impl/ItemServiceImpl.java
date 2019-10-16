package com.shenghao.item.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenghao.item.service.ItemService;
import com.shenghao.mapper.TbItemCatMapper;
import com.shenghao.mapper.TbItemDescMapper;
import com.shenghao.mapper.TbItemMapper;
import com.shenghao.mapper.TbItemParamItemMapper;
import com.shenghao.pojo.*;
import com.shenghao.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    /**
     * 查询所有商品并分页
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo((byte)1);
        List<TbItem> list = this.tbItemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        PageResult result = new PageResult();
        result.setPageIndex(page);
        result.setTotalPage(pageInfo.getTotal());
        result.setResult(list);
        return result;
    }

    /**
     * 持久化TbItem
     * @param tbItem
     * @return
     */
    @Override
    @LcnTransaction
    public Integer insertTbItem(TbItem tbItem) {
        return this.tbItemMapper.insert(tbItem);
    }

    /**
     * 更新与删除商品
     * 删除是指更新status字段的值，修改为3
     * @param tbItem
     * @return
     */
    @Override
    @LcnTransaction
    public Integer updateItemById(TbItem tbItem) {
        tbItem.setUpdated(new Date());
        return this.tbItemMapper.updateByPrimaryKeySelective(tbItem);
    }

    @Override
    public Map<String, Object> preUpdateItem(Long itemId) {
        Map<String, Object> map = new HashMap<>();

        //根据商品id查询商品
        TbItem tbItem = this.tbItemMapper.selectByPrimaryKey(itemId);
        map.put("item", tbItem);

        //根据商品id查询商品描述
        TbItemDesc tbItemDesc = this.tbItemDescMapper.selectByPrimaryKey(itemId);
        map.put("itemDesc", tbItemDesc.getItemDesc());

        //根据商品id查询商品类目
        TbItemCat itemCat = this.tbItemCatMapper.selectByPrimaryKey(tbItem.getCid());
        map.put("itemCat", itemCat.getName());

        //根据商品ID查询商品规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = this.tbItemParamItemMapper.selectByExampleWithBLOBs(example);//查询结果有大对象
        if(list != null && list.size() > 0){
            map.put("itemParamItem", list.get(0));
        }
        return map;
    }

    /**
     * 根据商品id查询商品信息
     * @param itemId
     * @return
     */
    @Override
    public TbItem selectItemInfo(Long itemId) {
        return this.tbItemMapper.selectByPrimaryKey(itemId);
    }
}
