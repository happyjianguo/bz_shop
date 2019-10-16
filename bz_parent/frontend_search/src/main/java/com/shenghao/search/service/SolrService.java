package com.shenghao.search.service;

import com.shenghao.utils.Result;
import com.shenghao.utils.SolrDocument;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SolrService {

    Result importAll();

    List<SolrDocument> selectByq(String q, Long page, Integer pageSize);
}
