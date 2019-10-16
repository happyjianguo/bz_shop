package com.shenghao.sso.controller;

import com.shenghao.pojo.TbUser;
import com.shenghao.sso.service.SSOService;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户注册与登陆
 */
@RestController
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private SSOService ssoService;

    /**
     * 对用户的注册信息(用户名与电话号码)做校验
     */
    @RequestMapping("/checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable String checkValue, @PathVariable Integer checkFlag){
        try{
            return this.ssoService.checkUserInfo(checkValue, checkFlag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 用户注册
     */
    @RequestMapping("/userRegister")
    public Result userRegister(TbUser tbUser){
        try{
            return this.ssoService.userRegister(tbUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 用户登陆
     */
    @RequestMapping("/userLogin")
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
        try{
            return this.ssoService.userLogin(username, password, request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 用户退出登陆
     */
    @RequestMapping("/logOut")
    public Result logOut(String token){
        try{
            return this.ssoService.logOut(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
