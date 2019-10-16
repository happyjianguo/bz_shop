package com.shenghao.common.redis.service;

import com.shenghao.utils.CatResult;
import org.springframework.web.bind.annotation.RequestBody;

public interface ItemCategoryService {

    void insertItemCategory(CatResult catResult);

    CatResult selectItemCategory();
}
