package com.shenghao.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 封装首页商品查询分类信息
 */
public class CatNode implements Serializable {
    /**
     * 对应文档中的数据格式
     */
    @JsonProperty("n")
    private String name;
    @JsonProperty("i")
    private List<?> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
