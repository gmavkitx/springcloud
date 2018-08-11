package com.kingboy.zuul.common.config;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 为provider服务设置回退处理器, 优先级比全局fallback要高.
 * <p>
 *     实验证明,要加@Configuration一类的注入注解
 * </p>
 * @author kingboy - KingBoyWorld@163.com
 * @since 2018/8/12 03:28
 */
//@Configuration
public class ConsumerFallbackProvider implements FallbackProvider {

    /**
     * 写的是ServiceId.
     *
     * @return java.lang.String
     */
    @Override
    public String getRoute() {
        return "service-provider";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("{\"message\":\"Provider Fallback\"}".getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.add("message", "provider fallback");
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
