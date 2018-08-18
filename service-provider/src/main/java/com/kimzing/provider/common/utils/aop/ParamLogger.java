package com.kimzing.provider.common.utils.aop;

import com.alibaba.fastjson.JSONObject;
import com.kimzing.utils.random.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 参数打印.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "king.log.enabled", havingValue = "true")
public class ParamLogger {

    @Pointcut("execution(* com.kimzing.provider.web..*(..))")
    public void controller() {
    }

    @Pointcut("execution(* com.kimzing.provider.service..*(..))")
    public void service() {
    }

    @Pointcut("execution(* com.kimzing.provider.mapper..*(..))")
    public void repository() {
    }

    @Before("controller()")
    public void controller(JoinPoint point) {

    }

    @Around("controller()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String uuid = RandomUtil.uuid();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("\n\t请求标识: {}\n\t请求IP: {}\n\t请求路径: {}\n\t请求方式: {}\n\t方法描述: {}\n\t请求参数: {}",
                uuid, request.getRemoteAddr(), request.getRequestURL(), request.getMethod(),
                getMethodInfo(point), JSONObject.toJSONString(request.getParameterMap()));

        long startTime = System.currentTimeMillis();
        Object[] args = point.getArgs();
        Object retVal = point.proceed(args);
        long endTime = System.currentTimeMillis();

        log.info("\n\t请求标识: {} \n\t执行时间: {} ms \n\t返回值: {}", uuid, endTime - startTime, JSONObject.toJSONString(retVal));
        return retVal;
    }

    @Before("service()")
    public void service(JoinPoint point) {
        log.info("\n\tservice method: {}", getMethodInfo(point));
    }

    @Before("repository()")
    public void repository(JoinPoint point) {
        log.info("\n\trepository method: {}", getMethodInfo(point));
    }

    private String getMethodInfo(JoinPoint point) {
        ConcurrentHashMap<String, Object> info = new ConcurrentHashMap<>(3);

        info.put("class", point.getTarget().getClass().getSimpleName());
        info.put("method", point.getSignature().getName());

        String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
        ConcurrentHashMap<String, String> args = null;

        if (Objects.nonNull(parameterNames)) {
            args = new ConcurrentHashMap<>(parameterNames.length);
            for (int i = 0; i < parameterNames.length; i++) {
                String value = point.getArgs()[i] != null ? point.getArgs()[i].toString() : "null";
                args.put(parameterNames[i], value);
            }
            info.put("args", args);
        }

        return JSONObject.toJSONString(info);
    }

}
