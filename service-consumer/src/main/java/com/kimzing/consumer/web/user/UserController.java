package com.kimzing.consumer.web.user;

import com.kimzing.consumer.client.provider.UserClient;
import com.kimzing.consumer.client.provider.dto.user.UserDTO;
import com.kimzing.consumer.client.provider.vo.user.UserVO;
import com.kimzing.consumer.stream.channel.ProviderInputAndOutPut;
import com.kimzing.utils.page.Page;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 测试通过feign访问provider服务.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@RestController
@RequestMapping("/consumer/user")
public class UserController {

    @Resource
    ProviderInputAndOutPut providerInputAndOutPut;

    @Resource
    UserClient providerClient;

    @ApiOperation(value = "新增User", tags = "用户信息")
    @PostMapping
    public UserVO save(@RequestBody UserDTO userDTO) {
        return providerClient.save(userDTO);
    }

    @ApiOperation(value = "更新User", tags = "用户信息")
    @PutMapping
    public UserVO update(@RequestBody UserDTO userDTO) {
        return providerClient.update(userDTO);
    }

    @ApiOperation(value = "移除User", tags = "用户信息")
    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable Long id) {
        providerClient.remove(id);
    }

    /**
     * 开启断路器次方法断路器，一旦生效，feign的断路器就执行不到了.
     * <p>
     *     定义熔断规则
     * </p>
     * @param id
     * @return com.kimzing.consumer.client.provider.vo.user.UserVO
     */
    @HystrixCommand(commandProperties = {

            //开启熔断，默认true
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //设置熔断的阈值，只有当并发超过指定个数时，才会判断熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "50"),
            //熔断的睡眠时间，例如设置5000，则熔断后，5s之后会进行再次尝试
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            //当错误超出设置的比例时，并且并发数超过上面设置的值时，就会进行熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            //更改为信号量模式
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
            @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "50"),
            @HystrixProperty(name = "execution.timeout.enabled", value = "false"),
    }, threadPoolProperties = {
            //设置最大的线程数，默认10
            @HystrixProperty(name = "maximumSize", value = "50"),
            @HystrixProperty(name = "coreSize", value = "50")
    })
    @ApiOperation(value = "根据ID查询User", tags = "用户信息")
    @GetMapping(value = "/{id}")
    public UserVO get(@PathVariable Long id) {
        UserVO userVO = providerClient.get(id);
        //使用注入的方式进行发送消息,会转换为json,虽然类型不一样，消费者还是可以收到
        providerInputAndOutPut.consumer().send(MessageBuilder.withPayload(userVO).build());
        return userVO;
    }

    /**
     * 开启断路器次方法断路器，一旦生效，feign的断路器就执行不到了.
     * <p>
     *     定义熔断规则
     * </p>
     * @param page
     * @param size
     * @return com.kimzing.utils.page.Page
     */
    @HystrixCommand(fallbackMethod = "listFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
    }, threadPoolProperties = {
            @HystrixProperty(name = "maximumSize", value = "50")
    })
    @ApiOperation(value = "分页查询User", tags = "用户信息")
    @GetMapping(value = "/list")
    public Page list(@RequestParam Long page, @RequestParam Long size) {
        return providerClient.list(page, size);
    }

    private Page listFallback(Long page, Long size) {
        return new Page(-1L, -1L, -1L, Collections.emptyList());
    }

}

