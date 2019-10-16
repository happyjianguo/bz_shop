package com.shenghao.order.controller;

import com.shenghao.order.service.OrderService;
import com.shenghao.pojo.TbOrder;
import com.shenghao.pojo.TbOrderItem;
import com.shenghao.pojo.TbOrderShipping;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @RequestMapping("/insertOrder")
    public Result insertOrder(String orderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping){
        try{
            Result res = Result.formatObjectToList(orderItem, TbOrderItem.class);
            List<TbOrderItem> list = (List<TbOrderItem>) res.getData();
            return this.orderService.insertOrder(list, tbOrder, tbOrderShipping);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
