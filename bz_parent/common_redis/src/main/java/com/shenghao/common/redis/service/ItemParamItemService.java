package com.shenghao.common.redis.service;

import com.shenghao.pojo.TbItemParamItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemParamItemService {

    void insertItemParamItem(TbItemParamItem tbItemParamItem);

    TbItemParamItem selectItemParamItem(Long itemId);
}
