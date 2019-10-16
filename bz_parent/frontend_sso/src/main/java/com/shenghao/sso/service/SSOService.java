package com.shenghao.sso.service;

import com.shenghao.pojo.TbUser;
import com.shenghao.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SSOService {

    Result checkUserInfo(String checkValue, Integer checkFlag);

    Result userRegister(TbUser tbUser);

    Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    Result logOut(String token);
}
