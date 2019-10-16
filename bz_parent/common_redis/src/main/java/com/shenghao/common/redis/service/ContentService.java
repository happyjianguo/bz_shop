package com.shenghao.common.redis.service;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ContentService {

    void insertContentAD(List<Map> list);

    List<Map> selectContentAD();
}
