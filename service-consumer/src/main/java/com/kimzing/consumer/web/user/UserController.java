package com.kimzing.consumer.web.user;

import com.kimzing.consumer.client.provider.UserClient;
import com.kimzing.consumer.client.provider.dto.user.UserDTO;
import com.kimzing.consumer.client.provider.vo.user.UserVO;
import com.kimzing.utils.page.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @ApiOperation(value = "根据ID查询User", tags = "用户信息")
    @GetMapping(value = "/{id}")
    public UserVO get(@PathVariable Long id) {
        return providerClient.get(id);
    }

    @ApiOperation(value = "分页查询User", tags = "用户信息")
    @GetMapping(value = "/list")
    public Page list(@ModelAttribute Page page) {
        return providerClient.list(page);
    }

}

