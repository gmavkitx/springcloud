package com.kimzing.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 生产者服务.
 *
 * @author kim - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@EnableHystrixDashboard
@SpringBootApplication
public class ServerHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerHystrixApplication.class, args);
    }
}
