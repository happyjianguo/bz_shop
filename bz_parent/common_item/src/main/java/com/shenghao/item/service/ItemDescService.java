package com.shenghao.item.service;

import com.shenghao.pojo.TbItemDesc;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemDescService {

    Integer insertItemDesc(TbItemDesc tbItemDesc);

    Integer updateItemDesc(TbItemDesc tbItemDesc);

    TbItemDesc selectItemDescByItemId(Long itemId);
}
