package com.shenghao.item.service;

import com.shenghao.pojo.TbItem;
import com.shenghao.utils.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface ItemService {

    PageResult selectTbItemAllByPage(Integer page, Integer rows);

    Integer insertTbItem(TbItem tbItem);

    Integer updateItemById(TbItem tbItem);

    Map<String, Object> preUpdateItem(Long itemId);

    TbItem selectItemInfo(@RequestParam Long itemId);
}
