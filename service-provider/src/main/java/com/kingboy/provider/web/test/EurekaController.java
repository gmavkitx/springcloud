package com.kingboy.provider.web.test;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取Eureka的相关服务信息.
 *
 * @author kingboy - KingBoyWorld@163.com
 * @since 2018/8/11 03:30
 */
@RestController
@RequestMapping("/eureka")
public class EurekaController {

    @Resource
    DiscoveryClient discoveryClient;

    @ApiOperation(value = "获取Eureka中的服务信息", tags = "EurekaServer")
    @GetMapping(value = "/services")
    public List<String> listService() {
        List<String> services = discoveryClient.getServices();
        return services;
    }

}
