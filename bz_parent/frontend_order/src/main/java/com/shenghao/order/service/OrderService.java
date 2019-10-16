package com.shenghao.order.service;

import com.shenghao.pojo.TbOrder;
import com.shenghao.pojo.TbOrderItem;
import com.shenghao.pojo.TbOrderShipping;
import com.shenghao.utils.Result;

import java.util.List;

public interface OrderService {

    Result insertOrder(List<TbOrderItem> tbOrderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping);
}
