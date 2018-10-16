package com.kimzing.provider.web.test;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试ConfigServer的动态刷新.
 *
 * @author kim - kimzing@163.com
 * @since 2018/8/11 03:27
 */
@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${config.version}")
    private String configVersion;

    @ApiOperation(value = "测试动态获取配置", tags = "ConfigServer")
    @GetMapping("/value")
    public String getRefreshValue() {
        return configVersion;
    }

}
