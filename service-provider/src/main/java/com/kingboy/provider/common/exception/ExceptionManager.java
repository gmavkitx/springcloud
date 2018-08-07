package com.kingboy.provider.common.exception;


import com.kingboy.common.utils.uuid.UUIDUtils;
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
 * <p></p>
 *
 * @author KingBoy - KingBoyWorld@163.com
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
     * <p></p>
     * @param codes
     * @return
     * @author KingBoy - KingBoyWorld@163.com
     * @since 2018/8/7 02:21
     */
    public ApiException create(String... codes) {
        ErrorMessage[] errorMessages = Stream.of(codes)
                .map(code -> new ErrorMessage(code, environment.getProperty(code)))
                .toArray(ErrorMessage[]::new);
        return new ApiException(UUIDUtils.getUUID(), appName, serverIp, errorMessages);
    }

    /**
     * 通过错误消息直接创建异常.
     * <p></p>
     * @param message
     * @return
     * @author KingBoy - KingBoyWorld@163.com
     * @since 2018/8/7 02:21
     */
    public ApiException create(String message) {
        return new ApiException(UUIDUtils.getUUID(), appName, serverIp, new ErrorMessage("COMMON", message));
    }

    /**
     * 简化异常栈信息.
     * <p></p>
     * @param apiException
     * @return
     * @author KingBoy - KingBoyWorld@163.com
     * @since 2018/8/7 02:21
     */
    protected ApiException create(ApiException apiException) {
        List<StackTraceElement> traceList = Stream.of(apiException.getStackTrace())
                .filter(p -> p.getClassName().contains("com.kingboy"))
                .filter(p -> !p.getClassName().contains("$"))
                .filter(p -> !p.getClassName().contains(".exception."))
                .collect(Collectors.toList());
        apiException.setStackTrace(traceList.toArray(new StackTraceElement[]{}));
        return apiException;
    }

}
