package com.shenghao.item.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.shenghao.item.service.ItemParamItemService;
import com.shenghao.mapper.TbItemParamItemMapper;
import com.shenghao.pojo.TbItemParamItem;
import com.shenghao.pojo.TbItemParamItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    @LcnTransaction
    public Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem) {
        return this.tbItemParamItemMapper.insert(tbItemParamItem);
    }

    @Override
    @LcnTransaction
    public Integer updateItemParamItem(TbItemParamItem tbItemParamItem) {
        tbItemParamItem.setUpdated(new Date());
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(tbItemParamItem.getItemId());
        return this.tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem, example);
    }

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    @Override
    public TbItemParamItem  selectTbItemParamItemByItemId(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = this.tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
