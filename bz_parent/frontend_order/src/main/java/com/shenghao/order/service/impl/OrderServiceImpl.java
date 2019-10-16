package com.shenghao.order.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.shenghao.mapper.TbOrderItemMapper;
import com.shenghao.mapper.TbOrderMapper;
import com.shenghao.mapper.TbOrderShippingMapper;
import com.shenghao.order.feign.CommonRedisFeignClient;
import com.shenghao.order.feign.FrontendCartFeignClient;
import com.shenghao.order.service.OrderService;
import com.shenghao.pojo.TbOrder;
import com.shenghao.pojo.TbOrderItem;
import com.shenghao.pojo.TbOrderShipping;
import com.shenghao.utils.IDUtils;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单服务业务层
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Autowired
    private FrontendCartFeignClient frontendCartFeignClient;

    /**
     * 创建订单
     * @param tbOrderItem
     * @param tbOrder
     * @param tbOrderShipping
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertOrder(List<TbOrderItem> tbOrderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping) {
        //获取订单id
        Long orderId = this.commonRedisFeignClient.selectOrderId();

        //补齐Order数据
        tbOrder.setOrderId(orderId.toString());
        tbOrder.setStatus(1);//未付款
        Date date = new Date();
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);
        tbOrder.setBuyerRate(0);//未评价
        //将订单插入到数据库中
        this.tbOrderMapper.insert(tbOrder);
        //插入订单中所包含的商品
        for(TbOrderItem item : tbOrderItem){
            item.setId(IDUtils.genItemId() + "");
            item.setOrderId(orderId.toString());
            this.tbOrderItemMapper.insert(item);

            //将订单中的商品从购物车中删除
            this.frontendCartFeignClient.deleteItemFromCart(Long.parseLong(item.getItemId()), tbOrder.getUserId().toString());
        }

        //插入物流表数据
        tbOrderShipping.setOrderId(orderId.toString());
        tbOrderShipping.setUpdated(date);
        tbOrderShipping.setCreated(date);
        this.tbOrderShippingMapper.insert(tbOrderShipping);

        return Result.ok(orderId);
    }
}
