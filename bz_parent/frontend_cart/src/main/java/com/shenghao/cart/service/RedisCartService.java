package com.shenghao.cart.service;

import com.shenghao.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RedisCartService {

    Result addItem(Long itemId, Integer num, String userId);

    Result showCart(String userId);

    Result updateItemNum(Long itemId, Integer num, String userId);

    Result deleteItemFromCart(Long itemId, String userId);

    Result goSettlement(String[] ids, String userId);
}
