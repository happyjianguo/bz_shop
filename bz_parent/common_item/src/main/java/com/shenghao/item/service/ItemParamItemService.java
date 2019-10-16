package com.shenghao.item.service;

import com.shenghao.pojo.TbItemParamItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemParamItemService {
    Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem);

    Integer updateItemParamItem(TbItemParamItem tbItemParamItem);

    TbItemParamItem selectTbItemParamItemByItemId(Long itemId);
}
