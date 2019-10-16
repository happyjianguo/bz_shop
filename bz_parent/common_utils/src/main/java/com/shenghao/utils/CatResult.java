package com.shenghao.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给首页内容分类的数据模型
 */
public class CatResult implements Serializable {
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
