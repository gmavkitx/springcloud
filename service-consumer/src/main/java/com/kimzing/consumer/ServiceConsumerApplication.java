package com.kimzing.consumer;

import com.didispace.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 生产者服务.
 * <p>
 *     @SpringCloudApplication = @SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker
 * </p>
 * @author KimZing - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@EnableSwagger2Doc
@EnableFeignClients
@SpringCloudApplication
public class ServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }
}
