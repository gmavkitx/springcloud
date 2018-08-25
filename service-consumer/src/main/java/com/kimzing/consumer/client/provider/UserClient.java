package com.kimzing.consumer.client.provider;

import com.kimzing.consumer.client.provider.dto.user.UserDTO;
import com.kimzing.consumer.client.provider.vo.user.UserVO;
import com.kimzing.utils.page.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * 下游服务提供的API.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018/8/24 14:28
 */
@FeignClient(value = "service-provider", fallback = UserClient.UserFallback.class)
public interface UserClient {

    @PostMapping(value = "/user")
    UserVO save(@RequestBody UserDTO userDTO);

    @PutMapping(value = "/user")
    UserVO update(@RequestBody UserDTO userDTO);

    @DeleteMapping(value = "/user/{id}")
    void remove(@PathVariable(value = "id") Long id);

    @GetMapping(value = "/user/{id}")
    UserVO get(@PathVariable(value = "id") Long id);

    @GetMapping(value = "/user/list")
    Page list(@RequestParam(value = "page") Long page, @RequestParam(value = "size") Long size);

    @Component
    class UserFallback implements UserClient {

        @Override
        public UserVO save(UserDTO userDTO) {
            return null;
        }

        @Override
        public UserVO update(UserDTO userDTO) {
            return null;
        }

        @Override
        public void remove(Long id) {

        }

        @Override
        public UserVO get(Long id) {
            return new UserVO();
        }

        @Override
        public Page list(Long page, Long size) {
            return new Page(-1L, -1L, -1L, Collections.emptyList());
        }
    }
}
