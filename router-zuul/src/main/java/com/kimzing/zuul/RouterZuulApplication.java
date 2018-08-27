package com.kimzing.zuul;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul网关路由.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@EnableSwaggerButler
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class RouterZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouterZuulApplication.class, args);
    }

}
