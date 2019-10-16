package com.shenghao.sso.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.shenghao.mapper.TbUserMapper;
import com.shenghao.pojo.TbUser;
import com.shenghao.pojo.TbUserExample;
import com.shenghao.sso.service.SSOService;
import com.shenghao.utils.*;
import com.shenghao.sso.feign.CommonRedisFeignClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    /**
     * 用户注册数据校验
     * @param checkValue
     * @param checkFlag
     * @return
     */
    @Override
    public Result checkUserInfo(String checkValue, Integer checkFlag) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if(checkFlag == 1){
            criteria.andUsernameEqualTo(checkValue);
        }else if(checkFlag == 2){
            criteria.andPhoneEqualTo(checkValue);
        }
        Integer rows = this.tbUserMapper.countByExample(example);
        if(rows > 0){
            return Result.error("数据不可用");
        }
        return Result.ok(checkValue);
    }

    /**
     * 用户注册接口
     * @param user
     * @return
     */
    @Override
    @LcnTransaction
    public Result userRegister(TbUser user) {
        //将密码做加密处理
        String pwd = MD5Utils.digest(user.getPassword());
        user.setPassword(pwd);
        //补齐数据
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        this.tbUserMapper.insert(user);
        return Result.ok();
    }

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //根据用户名密码查询数据库
        TbUser tbUser = this.login(username, password);
        if(tbUser == null){
            return Result.error("用户名或密码有误，请重新输入");
        }
        //将用户添加到redis中
        String userToken = UUID.randomUUID().toString();
        Integer flag = this.insertUserToRedis(tbUser, userToken);
        if(flag == 500){
            return Result.error("登陆失败");
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", userToken);
        map.put("userid", tbUser.getId().toString());
        map.put("username", tbUser.getUsername());

        //将临时购物车中的商品同步到永久购物车中
        this.syncCart(tbUser.getId().toString(), request);

        //同步购物车成功后，需要将临时购物车中的商品删除掉
        this.deleteCookieCart(request, response);
        return Result.ok(map);
    }

    /**
     * 同步购物车
     * @param userId
     * @param request
     */
    private void syncCart(String userId, HttpServletRequest request) {
        //获取临时购物车
        Map<String, CartItem> cookieCart = getCart(request);
        //获取永久购物车
        Map<String, CartItem> redisCart = getCart(userId);
        //删除永久购物车中所包含的l临时购物车中的商品
        Set<String> keys = cookieCart.keySet();
        for(String key : keys){
            redisCart.remove(key);
        }
        //将同步后的购物车缓存到redis中
        redisCart.putAll(cookieCart);
        //将永久购物车重新缓存到redis中
        this.addCartToRedis(userId, redisCart);
    }

    /**
     * 1.获取临时购物车
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

    /**
     * 2.获取永久购物车
     * @param userId
     * @return
     */
    private Map<String, CartItem> getCart(String userId) {
        try{
            Map<String, CartItem> cart = this.commonRedisFeignClient.selectCartByUserId(userId);
            if(cart == null){
                cart = new HashMap<String, CartItem>();
            }
            return cart;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<String, CartItem>();
    }

    /**
     * 将永久购物车重新缓存到redis中
     */
    private void addCartToRedis(String userId, Map<String, CartItem> cart) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("cart", cart);
        this.commonRedisFeignClient.insertCart(map);
    }

    /**
     * 用户退出登陆
     * @param token
     * @return
     */
    @Override
    public Result logOut(String token) {
        try{
            this.commonRedisFeignClient.logOut(token);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("退出失败");
    }

    /**
     * 将用户添加到redis中
     * @param tbUser
     * @param userToken
     */
    private Integer insertUserToRedis(TbUser tbUser, String userToken) {
        try {
            this.commonRedisFeignClient.insertUser(tbUser, userToken);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 500;
    }

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    private TbUser login(String username, String password) {
        String pwd = MD5Utils.digest(password);
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(pwd);
        List<TbUser> list = this.tbUserMapper.selectByExample(example);
        if(list == null || list.size() <= 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 删除临时购物车
     * @param request
     * @param response
     */
    private void deleteCookieCart(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, cart_cookie_name);
    }
}
