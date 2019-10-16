package com.shenghao.common.redis.controller;

import com.shenghao.common.redis.service.SSOService;
import com.shenghao.pojo.TbUser;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存用户
 */
@RestController
@RequestMapping("/sso/redis")
public class SSOController {

    @Autowired
    private SSOService ssoService;

    /**
     * 将用户缓存到Redis中
     */
    @RequestMapping("/insertUser")
    public void insertUser(@RequestBody TbUser user, @RequestParam String userToken){
        this.ssoService.insertUser(user, userToken);
    }

    /**
     * 用户退出登陆
     */
    @RequestMapping("/logOut")
    public void logOut(@RequestParam String userToken){
        this.ssoService.logOut(userToken);
    }

    /**
     * 根据用户token校验用户在redis中是否失效
     */
    @RequestMapping("/checkUserToken")
    public TbUser checkUserToken(@RequestParam String token){
        return this.ssoService.checkUserToken(token);
    }
}
