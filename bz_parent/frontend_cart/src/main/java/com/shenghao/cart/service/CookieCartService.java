package com.shenghao.cart.service;

import com.shenghao.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieCartService {

    Result addItem(Long itemId, Integer num,
                   HttpServletRequest request,
                   HttpServletResponse response);

    Result showCart(HttpServletRequest request, HttpServletResponse response);

    Result updateItemNum(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

    Result deleteItemFromCart(Long itemId, String userId, HttpServletRequest request, HttpServletResponse response);
}
