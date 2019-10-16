package com.shenghao.frontend.portal.service.impl;

import com.shenghao.frontend.portal.feign.CommonContentFeignClient;
import com.shenghao.frontend.portal.feign.CommonRedisFeignClient;
import com.shenghao.frontend.portal.service.ContentService;
import com.shenghao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    /**
     * 查询首页大广告位
     * @return
     */
    @Override
    public Result selectFrontendContentByAD() {
        //查询缓存
        try{
            List<Map> list = this.commonRedisFeignClient.selectContentAD();
            if(list != null && list.size() > 0){
                return Result.ok(list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //查询数据库
        List<Map> list = this.commonContentFeignClient.selectFrontendContentByAD();

        //将查询到的数据添加到缓存中
        if(list != null && list.size() > 0){
            this.commonRedisFeignClient.insertContentAD(list);
        }

        //返回
        if(list != null && list.size() > 0){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }
}
