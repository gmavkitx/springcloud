package com.kimzing.consumer.common.utils.exception;


import com.kimzing.utils.random.RandomUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 异常生产工厂.
 *
 * @author kim - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@Component
@PropertySource(value = "exception.properties", encoding = "UTF-8")
public class ExceptionManager {

    /**
     * 服务名称
     */
    @Value("${spring.application.name}")
    private String appName;

    /**
     * 服务端IP
     */
    @Value("${spring.cloud.client.hostname}")
    private String serverIp;

    @Resource
    Environment environment;

    /**
     * 创建默认的异常.
     *
     * @param codes
     * @return
     */
    public ApiException create(String... codes) {
        ErrorMessage[] errorMessages = Stream.of(codes)
                .map(code -> new ErrorMessage(code, environment.getProperty(code)))
                .toArray(ErrorMessage[]::new);
        return new ApiException(RandomUtil.uuid(), appName, serverIp, errorMessages);
    }

    /**
     * 通过错误消息直接创建异常.
     *
     * @param message
     * @return
     */
    public ApiException create(String message) {
        return new ApiException(RandomUtil.uuid(), appName, serverIp, new ErrorMessage("COMMON", message));
    }

    /**
     * 简化异常栈信息.
     *
     * @param apiException
     * @return
     */
    protected ApiException create(ApiException apiException) {
        List<StackTraceElement> traceList = Stream.of(apiException.getStackTrace())
                .filter(p -> p.getClassName().contains("com.kimzing"))
                .filter(p -> !p.getClassName().contains("$"))
                .filter(p -> !p.getClassName().contains(".exception."))
                .collect(Collectors.toList());
        apiException.setStackTrace(traceList.toArray(new StackTraceElement[]{}));
        return apiException;
    }

}
