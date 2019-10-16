package com.shenghao.cart.service;

import com.shenghao.pojo.TbUser;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserCheckService {

    TbUser checkUserToken(String token);
}
