package com.shenghao.item.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.shenghao.item.service.ItemDescService;
import com.shenghao.mapper.TbItemDescMapper;
import com.shenghao.pojo.TbItemDesc;
import com.shenghao.pojo.TbItemDescExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    @LcnTransaction
    public Integer insertItemDesc(TbItemDesc tbItemDesc) {
        return this.tbItemDescMapper.insert(tbItemDesc);
    }

    /**
     * 更新商品描述
     * @param tbItemDesc
     * @return
     */
    @Override
    @LcnTransaction
    public Integer updateItemDesc(TbItemDesc tbItemDesc) {
        tbItemDesc.setUpdated(new Date());
        return this.tbItemDescMapper.updateByPrimaryKey(tbItemDesc);
    }

    /**
     * 根据商品id查询商品描述信息
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc selectItemDescByItemId(Long itemId) {
        //由于返回的是text类型（大字段），所以不能直接用selectByPrimaryKey
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemDesc> list = this.tbItemDescMapper.selectByExampleWithBLOBs(example);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
