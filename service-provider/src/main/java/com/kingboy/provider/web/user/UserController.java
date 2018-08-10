package com.kingboy.provider.web.user;

import com.kingboy.common.utils.page.Page;
import com.kingboy.provider.domain.dto.user.UserDTO;
import com.kingboy.provider.domain.vo.user.UserVO;
import com.kingboy.provider.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User控制层.
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation(value = "新增User", tags = "用户信息")
    @PostMapping
    public UserVO save(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @ApiOperation(value = "更新User", tags = "用户信息")
    @PutMapping
    public UserVO update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @ApiOperation(value = "移除User", tags = "用户信息")
    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }

    @ApiOperation(value = "根据ID查询User", tags = "用户信息")
    @GetMapping(value = "/{id}")
    public UserVO get(@PathVariable Long id) {
        return userService.get(id);
    }

    @ApiOperation(value = "分页查询User", tags = "用户信息")
    @GetMapping(value = "/list")
    public Page list(@ModelAttribute Page page) {
        return userService.list(page);
    }

}

