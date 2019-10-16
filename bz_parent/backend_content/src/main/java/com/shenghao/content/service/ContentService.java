package com.shenghao.content.service;

import com.shenghao.pojo.TbContent;
import com.shenghao.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ContentService{

    Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

    Result insertTbContent(TbContent tbContent);

    Result deleteContentByIds(Long ids);

}
