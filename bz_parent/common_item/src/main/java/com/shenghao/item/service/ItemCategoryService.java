package com.shenghao.item.service;

import com.shenghao.pojo.TbItemCat;
import com.shenghao.utils.CatResult;

import java.util.List;

public interface ItemCategoryService {
    List<TbItemCat> selectItemCategoryByParentId(Long id);

    CatResult selectItemCategoryAll();
}
