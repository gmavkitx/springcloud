package com.kimzing.consumer.client.provider;

import com.kimzing.consumer.client.provider.dto.user.UserDTO;
import com.kimzing.consumer.client.provider.vo.user.UserVO;
import com.kimzing.utils.page.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 下游服务提供的API.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/8/24 14:28
 */
@FeignClient(value = "service-provider")
@RequestMapping(value = "/user")
public interface UserClient {

    @PostMapping
    UserVO save(@RequestBody UserDTO userDTO);

    @PutMapping
    UserVO update(@RequestBody UserDTO userDTO);

    @DeleteMapping(value = "/{id}")
    void remove(@PathVariable(value = "id") Long id);

    @GetMapping(value = "/{id}")
    UserVO get(@PathVariable(value = "id") Long id);

    @GetMapping(value = "/list")
    Page list(@ModelAttribute Page page);
}
