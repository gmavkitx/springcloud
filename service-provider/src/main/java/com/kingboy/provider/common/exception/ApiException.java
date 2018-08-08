package com.kingboy.provider.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import lombok.*;

/**
 * 自定义异常.
 * <p></p>
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@Setter
@Getter
@NoArgsConstructor
public class ApiException extends RuntimeException {

    public ApiException(ErrorMessage... errorMessages) {
        this.messages = errorMessages;
    }

    public ApiException(String id, String appName, String serverIp, ErrorMessage... errorMessages) {
        this.id = id;
        this.appName = appName;
        this.serverIp = serverIp;
        this.messages = errorMessages;
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
    private ErrorMessage[] messages;

    public String toJsonString() {
        SimplePropertyPreFilter filter= new SimplePropertyPreFilter(ApiException.class,"id","appName","serverIp","messages");
        return JSONObject.toJSONString(this, filter);
    }

}