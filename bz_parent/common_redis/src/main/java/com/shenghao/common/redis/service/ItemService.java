package com.shenghao.common.redis.service;

import com.shenghao.pojo.TbItem;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemService {

    void insertItemBasicInfo(TbItem tbItem);

    TbItem selectItemBasicInfo(@RequestParam Long tbItemId);
}
