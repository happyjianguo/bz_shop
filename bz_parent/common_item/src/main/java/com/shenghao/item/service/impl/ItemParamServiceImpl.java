package com.shenghao.item.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenghao.item.service.ItemParamService;
import com.shenghao.mapper.TbItemParamMapper;
import com.shenghao.pojo.TbItemParam;
import com.shenghao.pojo.TbItemParamExample;
import com.shenghao.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品参数
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list = this.tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(null != list && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询所有规格参数模板
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult selectItemParamAll(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = this.tbItemParamMapper.selectByExampleWithBLOBs(example);//表中有text类型字段

        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);

        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setResult(pageInfo.getList());
        pageResult.setTotalPage(pageInfo.getTotal());
        return pageResult;
    }

    /**
     * 添加商品分类规格参数模板
     * @param tbItemParam
     * @return
     */
    @Override
    @LcnTransaction
    public Integer insertItemParam(TbItemParam tbItemParam) {
        return this.tbItemParamMapper.insertSelective(tbItemParam);
    }

    /**
     * 根据id删除商品分类规格参数模板
     * @param id
     * @return
     */
    @Override
    public Integer deleteItemParamById(Long id) {
        return tbItemParamMapper.deleteByPrimaryKey(id);
    }


}
