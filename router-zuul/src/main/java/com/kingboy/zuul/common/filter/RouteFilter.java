package com.kingboy.zuul.common.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.Proxy;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

/**
 * TODO 路由过滤器.
 * <p>
 *     routeFilter在preFilter之后运行并向其他服务发出请求。 这里的大部分工作是将请求和响应数据转换为客户端所需的模型。 以下示例显示了routeFile：
 *     这个示例是将带有route参数的HTTP请求改成请求provider的服务，并将服务端的返回转换成Servlet的响应。
 *     仅当做示例,没有实际意义.
 * </p>
 * @author kingboy - KingBoyWorld@163.com
 * @since 2018/8/12 04:36
 */
@Component
public class RouteFilter extends ZuulFilter {

    @Autowired
    private ProxyRequestHelper helper;

    @Override
    public String filterType() {

        return ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = getCurrentContext();
        return context.getRequest().getParameter("route") != null;
    }

    @Override
    public Object run() {

        OkHttpClient httpClient = new OkHttpClient.Builder().proxy(Proxy.NO_PROXY).build();

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        Headers.Builder headers = new Headers.Builder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            Enumeration<String> values = request.getHeaders(name);

            while (values.hasMoreElements()) {
                String value = values.nextElement();
                headers.add(name, value);
            }
        }

        headers.removeAll("host");

        Request builder = new Request.Builder()
                .headers(headers.build())
                .url("http://localhost:7005/api/provider/user/list")
                .method("GET", null)
                .build();

        Response response = null;
        try {
            response = httpClient.newCall(builder).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();

        if (response != null) {
            response.headers().toMultimap().entrySet()
                    .forEach(entry -> responseHeaders.put(entry.getKey(), entry.getValue()));
        }

        try {
            if (response != null) {
                this.helper.setResponse(response.code(), response.body().byteStream(), responseHeaders);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.setRouteHost(null); // prevent SimpleHostRoutingFilter from running

        return null;
    }

}
