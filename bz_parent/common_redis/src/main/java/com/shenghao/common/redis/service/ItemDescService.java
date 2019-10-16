package com.shenghao.common.redis.service;

import com.shenghao.pojo.TbItemDesc;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemDescService {

    void insertItemDesc(TbItemDesc tbItemDesc);

    TbItemDesc selectItemDesc(Long itemId);
}
