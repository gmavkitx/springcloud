package com.kimzing.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka服务注册发现中心.
 *
 * @author kim - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@EnableEurekaServer
@SpringBootApplication
public class ServerEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerEurekaApplication.class, args);
    }

}
