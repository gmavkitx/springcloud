package com.kingboy.zuul.common.config;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 为全局服务设置回退处理器.
 * <p>
 *     实验证明,要加@Configuration一类的注入注解
 * </p>
 * @author kingboy - KingBoyWorld@163.com
 * @since 2018/8/12 03:33
 */
@Configuration
public class GlobalFallbackProvider implements FallbackProvider {

    /**
     * 返回*或者null则为所有服务设置fallback.
     *
     * @return java.lang.String
     */
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable throwable) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("{\"message\":\"Global Fallback\"}".getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", "global fallback");
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
