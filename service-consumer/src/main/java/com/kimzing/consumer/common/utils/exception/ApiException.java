package com.kimzing.consumer.common.utils.exception;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义异常.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018-08-07 02:02
 */
public class ApiException extends RuntimeException {

    public ApiException(ErrorMessage... errorMessages) {
        this.messages = Arrays.asList(errorMessages);
    }

    public ApiException(String id, String appName, String serverIp, ErrorMessage... errorMessages) {
        this.id = id;
        this.appName = appName;
        this.serverIp = serverIp;
        this.messages = Arrays.asList(errorMessages);
    }

    /**
     * 异常ID
     */
    private String id;

    /**
     * 服务名称
     */
    private String appName;

    /**
     * 服务端IP
     */
    private String serverIp;

    /**
     * 错误信息体
     */
    private List<ErrorMessage> messages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setMessages(List<ErrorMessage> messages) {
        this.messages = messages;
    }

    public List<ErrorMessage> getMessages() {
        return messages;
    }

    public String toJsonString() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(ApiException.class, "id", "appName", "serverIp", "messages");
        return JSONObject.toJSONString(this, filter);
    }

}
