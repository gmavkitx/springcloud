package com.kingboy.zuul.common.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

/**
 * 前置过滤器.
 *
 * @author kingboy - KingBoyWorld@163.com
 * <p>
 *     主要用例是设置routefilter所需的信息。
 *     以下过滤器演示修改RequestBody为大写, shouldfilter中设置了只有请求参数包含pre才会被处理
 * </p>
 * @since 2018/8/12 04:35
 */
@Component
public class PreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = getCurrentContext();
        return context.getRequest().getParameter("pre") != null;
    }

    @Override
    public Object run() {
        try {
            RequestContext context = getCurrentContext();
            InputStream in = (InputStream) context.get("requestEntity");
            if (in == null) {
                in = context.getRequest().getInputStream();
            }
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            body = body.toUpperCase();
            context.set("requestEntity", new ByteArrayInputStream(body.getBytes("UTF-8")));
        } catch (IOException e) {
            rethrowRuntimeException(e);
        }
        return null;
    }

}
