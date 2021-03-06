package com.kimzing.consumer.common.utils.exception;

import com.kimzing.utils.json.JsonUtil;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * Feignn异常解析.
 *
 * @author kim - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@Slf4j
@Configuration
public class FeignExceptionHandler implements ErrorDecoder {

    /**
     * Feign无响应异常
     */
    private static final ApiException FEIGN_FAIL_EXCEPTION = new ApiException(new ErrorMessage("FEIGN_ERROR", "调用服务无响应"));

    @Override
    @SneakyThrows
    public Exception decode(String methodKey, Response response) {
        if (response.body() != null) {
            String body = Util.toString(response.body().asReader());
            log.error("status: {} reading: {} body: {}", response.status(), methodKey, body);
            ApiException exception = JsonUtil.jsonToBean(body, ApiException.class);
            return exception;
        }
        return FEIGN_FAIL_EXCEPTION;
    }
}
