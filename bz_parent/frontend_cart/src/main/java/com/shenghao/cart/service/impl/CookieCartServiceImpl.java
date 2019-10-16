package com.shenghao.cart.service.impl;

import com.shenghao.cart.feign.CommonItemFeignClient;
import com.shenghao.cart.service.CookieCartService;
import com.shenghao.pojo.TbItem;
import com.shenghao.utils.CartItem;
import com.shenghao.utils.CookieUtils;
import com.shenghao.utils.JsonUtils;
import com.shenghao.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 用户未登录状态下的购物车操作
 */
@Service
public class CookieCartServiceImpl implements CookieCartService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    /**
     * 将商品添加到购物车当中
     *
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result addItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        //1.获取临时购物车
        Map<String, CartItem> cart = this.getCart(request);
        //2.查询商品
        TbItem item = this.selectItemById(itemId);
        //3.向购物车中添加商品
        this.addItemToCart(cart, item, num, itemId);
        //4.将购物车通过cookie写回给客户端浏览器
        this.addClientCookie(request, response, cart);
        return Result.ok();
    }

    /**
     * 查看购物车
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result showCart(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> list = new ArrayList<>();

        Map<String, CartItem> cart = this.getCart(request);
        Set<String> keys = cart.keySet();
        for(String key : keys){
            list.add(cart.get(key));
        }
        return Result.ok(list);
    }

    /**
     * 修改购物车中商品的数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result updateItemNum(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        Map<String, CartItem> cart = this.getCart(request);
        CartItem item = cart.get(itemId.toString());
        if(item != null){
            item.setNum(num);
        }
        this.addClientCookie(request, response, cart);
        return Result.ok();
    }

    /**
     * 删除购物车中的商品
     * @param itemId
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result deleteItemFromCart(Long itemId, String userId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, CartItem> cart = this.getCart(request);
        cart.remove(itemId.toString());
        this.addClientCookie(request, response, cart);
        return Result.ok();
    }

    /**
     * 将购物车通过Cookie写回给浏览器客户端
     * @param request
     * @param response
     * @param cart
     */
    private void addClientCookie(HttpServletRequest request, HttpServletResponse response, Map<String, CartItem> cart) {
        String cartJson = JsonUtils.objectToJson(cart);
        CookieUtils.setCookie(request, response, this.cart_cookie_name, cartJson, true);

    }

    /**
     * 将商品添加到购物车中
     * @param cart
     * @param item
     * @param num
     * @param itemId
     */
    private void addItemToCart(Map<String, CartItem> cart, TbItem item, Integer num, Long itemId) {
        //从购物车中取商品
        CartItem cItem = cart.get(itemId.toString());
        if(cItem == null){
            //没有相同的商品
            CartItem cartItem = new CartItem();
            cartItem.setId(item.getId());
            cartItem.setImage(item.getImage());
            cartItem.setNum(num);
            cartItem.setPrice(item.getPrice());
            cartItem.setSellPoint(item.getSellPoint());
            cartItem.setTitle(item.getTitle());
            cart.put(item.getId().toString(), cartItem);
        }else{
            //购物车中已经有了同样的商品，做数量递增
            cItem.setNum(cItem.getNum() + num);
        }
    }

    /**
     * 根据商品id查询商品
     * @param itemId
     * @return
     */
    private TbItem selectItemById(Long itemId) {
        TbItem tbItem = this.commonItemFeignClient.selectItemInfo(itemId);
        return tbItem;
    }

    /**
     * 获取购物车
     *
     * @param request
     * @return
     */
    private Map<String, CartItem> getCart(HttpServletRequest request) {
        String cartJson = CookieUtils.getCookieValue(request, this.cart_cookie_name, true);
        if (StringUtils.isBlank(cartJson)) {
            //临时购物车不存在
            return new HashMap<String, CartItem>();
        }
        try{
            //临时购物车已存在，需要做json转换
            Map<String, CartItem> map = JsonUtils.jsonToMap(cartJson, CartItem.class);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<String, CartItem>();//不影响业务逻辑
    }
}
