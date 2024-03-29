package com.shenghao.content.service;

import com.shenghao.utils.Result;

public interface ContentCategoryService {

    Result selectContentCategoryByParentId(Long id);

    Result insertContentCategory(Long parentId, String name);

    Result deleteContentCategoryById(Long categoryId);

    Result updateContentCategory(Long id, String name);

}
