package com.kimzing.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心.
 * <p>
 *     配置获取url示例:
 *     /{application}/{profile}[/{label}]
 *     /{application}-{profile}.yml
 *     /{label}/{application}-{profile}.yml
 *     /{application}-{profile}.properties
 *     /{label}/{application}-{profile}.properties
 * </p>
 * @author kim - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ServerConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerConfigApplication.class, args);
    }
}
