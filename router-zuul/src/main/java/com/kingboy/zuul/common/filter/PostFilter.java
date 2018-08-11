package com.kingboy.zuul.common.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

/**
 * 后置过滤器.
 * <p>
 *      后置过滤器通常用来操作操纵Response。 以下过滤器演示修改Response, shouldfilter中设置了只有请求参数包含post才会被处理
 * </p>
 * @author kingboy - KingBoyWorld@163.com
 * @since 2018/8/12 04:52
 */
@Component
public class PostFilter extends ZuulFilter {

    public String filterType() {
        return POST_TYPE;
    }

    public int filterOrder() {
        return 999;
    }

    public boolean shouldFilter() {
        RequestContext context = getCurrentContext();
        return context.getRequest().getParameter("post") != null;
    }

    public Object run() {
        try {
            RequestContext context = getCurrentContext();
            InputStream stream = context.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            context.setResponseBody("返回体已经被修改: " + body);
        } catch (IOException e) {
            rethrowRuntimeException(e);
        }
        return null;
    }

}
