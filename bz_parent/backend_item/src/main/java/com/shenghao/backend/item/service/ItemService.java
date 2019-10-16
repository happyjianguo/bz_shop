package com.shenghao.backend.item.service;

import com.shenghao.pojo.TbItem;
import com.shenghao.utils.Result;

public interface ItemService {
    Result selectTbItemAllByPage(Integer page, Integer rows);

    Result insertTbItem(TbItem tbItem, String desc, String itemParams) throws RuntimeException;

    Result deleteItemById(Long itemId);

    Result preUpdateItem(Long itemId);

    Result updateTbItem(TbItem tbItem, String desc, String itemParams);
}
