package com.shenghao.backend.item.service;

import com.shenghao.utils.Result;

public interface ItemCategoryService {

    Result selectItemCategoryByParentId(Long id);
}
