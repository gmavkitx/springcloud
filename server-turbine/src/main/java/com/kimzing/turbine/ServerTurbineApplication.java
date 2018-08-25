package com.kimzing.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 生产者服务.
 * <p>
 *     @SpringCloudApplication = @SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker
 * </p>
 * @author KimZing - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class ServerTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerTurbineApplication.class, args);
    }
}
