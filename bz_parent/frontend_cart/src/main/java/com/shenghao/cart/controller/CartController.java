package com.shenghao.cart.controller;

import com.shenghao.cart.service.CookieCartService;
import com.shenghao.cart.service.RedisCartService;
import com.shenghao.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CookieCartService cookieCartService;

    @Autowired
    private RedisCartService redisCartService;

    /**
     * 将商品加入购物车
     */
    @RequestMapping("/addItem")
    public Result addItem(String userId, Long itemId,
                          @RequestParam(defaultValue = "1")Integer num,
                          HttpServletRequest request,
                          HttpServletResponse response){
        try{
            if(StringUtils.isBlank(userId)){
                //在用户未登陆状态下
                return this.cookieCartService.addItem(itemId, num, request, response);
            }else{
                //用户已登陆状态下
                return this.redisCartService.addItem(itemId, num, userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 查看购物车
     */
    @RequestMapping("/showCart")
    public Result showCart(String userId, HttpServletRequest request, HttpServletResponse response){
        try{
            if(StringUtils.isBlank(userId)){
                //在用户未登陆状态下
                return this.cookieCartService.showCart(request, response);
            }else{
                //用户已登陆状态下
                return this.redisCartService.showCart(userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 修改购物车中商品的数量
     */
    @RequestMapping("/updateItemNum")
    public Result updateItemNum(Long itemId, String userId, Integer num,
                                HttpServletRequest request,
                                HttpServletResponse response){

        try{
            if(StringUtils.isBlank(userId)){
                //在用户未登陆状态下
                return this.cookieCartService.updateItemNum(itemId, num, request, response);
            }else{
                //用户已登陆状态下
                return this.redisCartService.updateItemNum(itemId, num, userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 删除购物车中的商品
     */
    @RequestMapping("/deleteItemFromCart")
    public Result deleteItemFromCart(@RequestParam Long itemId, @RequestParam String userId,
                                     HttpServletRequest request,
                                     HttpServletResponse response){
        try{
            if(StringUtils.isBlank(userId)){
                //在用户未登陆状态下
                return this.cookieCartService.deleteItemFromCart(itemId, userId, request, response);
            }else{
                //用户已登陆状态下
                return this.redisCartService.deleteItemFromCart(itemId, userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 去结算
     */
    @RequestMapping("/goSettlement")
    public Result goSettlement(String[] ids, String userId){
        try{
            return this.redisCartService.goSettlement(ids, userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
