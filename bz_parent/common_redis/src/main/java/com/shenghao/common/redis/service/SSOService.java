package com.shenghao.common.redis.service;

import com.shenghao.pojo.TbUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface SSOService {

    void insertUser(TbUser user, String userToken);

    void logOut(@RequestParam String userToken);

    TbUser checkUserToken(@RequestParam String token);

}
