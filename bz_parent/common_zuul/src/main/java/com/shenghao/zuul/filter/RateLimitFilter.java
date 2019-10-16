package com.shenghao.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 限流器
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    //创建令牌桶
    //RateLimiter.create(1)，以秒为单位，即1秒生成1个令牌
    //数值越大代表处理请求量越大，数值越小代表处理请求量越少
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 限流器的最高级应为最高
     * 数值越小优先级越高，这里设置为比最小优先级还小1
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    /**
     * 是否开启过滤器，默认不开启，改为true
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //是否能从令牌桶中获取到令牌
        if(!RATE_LIMITER.tryAcquire()){
            //拿不到令牌
            RequestContext requestContext = RequestContext.getCurrentContext();//拿到当前请求对象
            requestContext.setSendZuulResponse(false);//设置网关不给予响应
            requestContext.setResponseStatusCode(429);//http状态码中，429表示请求超过限制了
            System.out.println("限流");
        }
        return null;
    }
}
