package com.shenghao.cart.service.impl;

import com.shenghao.cart.feign.CommonRedisFeign;
import com.shenghao.cart.service.UserCheckService;
import com.shenghao.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 根据用户token校验用户是否失效业务层
 * 这么做是因为如果直接在拦截器中注入Feign会注入失败，报空指针异常，因为拦截器的加载优先级高于Feign
 */
@Service
public class UserCheckServiceImpl implements UserCheckService {

    @Autowired
    private CommonRedisFeign commonRedisFeign;

    @Override
    public TbUser checkUserToken(String token) {
        return this.commonRedisFeign.checkUserToken(token);
    }
}
