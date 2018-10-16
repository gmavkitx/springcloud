package com.kimzing.consumer.common.utils.exception;

import com.kimzing.utils.random.RandomUtil;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 全局异常捕捉并转换异常.
 *
 * @author kim - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.kimzing.provider.web")
public class GlobalExceptionHandler {

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
    ExceptionManager exceptionManager;

    /**
     * Service校验异常.
     *
     * @param e
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handlerException(ConstraintViolationException e) {
        String[] codes = e.getConstraintViolations().stream()
                .map(vio -> vio.getMessageTemplate())
                .toArray(String[]::new);
        return getExceptionStringByCode(codes, e.getStackTrace());
    }

    /**
     * Controller校验异常.
     *
     * @param e
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handlerException(MethodArgumentNotValidException e) {
        String[] codes = e.getBindingResult().getAllErrors().stream()
                .map(p -> p.getDefaultMessage())
                .toArray(String[]::new);

        return getExceptionStringByCode(codes, e.getStackTrace());
    }

    /**
     * Controller参数转换异常.
     *
     * @param e
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handlerException(HttpMessageConversionException e) {
        ApiException apiException = createByCodeAndMessage("CONVERT_ERROR", e);
        return apiException.toJsonString();
    }

    /**
     * 自定义异常.
     * <p></p>
     * @param e
     * @author kim - kimzing@163.com
     * @since 2018/8/7 02:21
     */
    @ExceptionHandler(ApiException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handlerException(ApiException e) {
        ApiException apiException = exceptionManager.create(e);
        log.error(logTraceInfo(apiException));
        return apiException.toJsonString();
    }

    /**
     * Hystrix断路器fallback异常.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String handleException(HystrixRuntimeException e) {
        String message = e.getFallbackException().getCause().getMessage();
        ApiException exception = new ApiException(RandomUtil.uuid(), appName, serverIp, new ErrorMessage("FALLBACK_EXCEPTION", message));
        exception.setStackTrace(e.getStackTrace());
        ApiException apiException = exceptionManager.create(exception);
        log.error(logTraceInfo(apiException));
        return apiException.toJsonString();
    }

    /**
     * 其它异常信息.
     *
     * @param e
     * @return java.lang.String
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        ApiException apiException = createByCodeAndMessage("SYSTEM_ERROR", e);
        return apiException.toJsonString();
    }

    /**
     * 通过code集合获取异常信息.
     *
     * @param codes
     * @param stackTrace
     * @return
     */
    private String getExceptionStringByCode(String[] codes, StackTraceElement[] stackTrace) {
        ApiException exception = exceptionManager.create(codes);
        exception.setStackTrace(stackTrace);

        ApiException apiException = exceptionManager.create(exception);
        log.error(logTraceInfo(apiException));
        return apiException.toJsonString();
    }

    /**
     * 自定义code,并从causeException中获取信息.
     *
     * @param e
     * @return
     */
    private ApiException createByCodeAndMessage(String code, Exception e) {
        ApiException apiException = new ApiException(RandomUtil.uuid(), appName, serverIp, new ErrorMessage(code, e.getMessage()));
        apiException.setStackTrace(e.getStackTrace());
        ApiException simpleApiException = exceptionManager.create(apiException);
        log.error(logTraceInfo(simpleApiException));
        log.error(logTraceInfo(e));
        return apiException;
    }

    /**
     * 取出异常栈信息.
     *
     * @param e
     * @return
     */
    private String logTraceInfo(Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(e);
        for (StackTraceElement traceElement : trace) {
            sb.append("\n\tat " + traceElement);
        }
        return sb.toString();
    }

}
