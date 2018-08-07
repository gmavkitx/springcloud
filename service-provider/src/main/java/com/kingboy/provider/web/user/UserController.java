package com.kingboy.provider.web.user;

import com.kingboy.provider.domain.dto.user.UserDTO;
import com.kingboy.provider.domain.vo.user.UserVO;
import com.kingboy.provider.service.user.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * User控制层.
 * <p></p>
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    //如果不需要返回信息，取消return
    @ApiOperation(value = "新增User", tags = "用户信息")
    @PostMapping
    public UserVO save(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    //如果不需要返回信息，取消return
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

    @ApiOperation(value = "根据条件分页查询User", tags = "用户信息")
    @PostMapping(value = "/list")
    public IPage<UserVO> list(@ModelAttribute Page page, @RequestBody(required = false) UserDTO userDTO) {
        return userService.list(page, userDTO);
    }

    //需要注意的是，不添加deleteFlag查询条件
    @ApiOperation(value = "分页查询-XML", tags = "用户信息")
    @GetMapping("/xml")
    public IPage<UserVO> listWithXML(@ModelAttribute Page page) {
        return userService.listWithXML(page);
    }

    //需要注意的是，不添加deleteFlag查询条件
    @ApiOperation(value = "分页查询-Annotation", tags = "用户信息")
    @GetMapping("/annotation")
    public IPage<UserVO> listWithAnnotation(@ModelAttribute Page page) {
        return userService.listWithAnnotation(page);
    }

    //需要注意的是，比较的值是数据库中存储的值，枚举类型使用的也是数据库中存储的值
    //自动添加deleteFlag查询条件
    @ApiOperation(value = "条件查询-Wrapper", tags = "用户信息")
    @PostMapping("/wrapper")
    public IPage<UserVO> listByConditionWithWrapper(@ModelAttribute Page page, @RequestBody UserDTO userDTO) {
        return userService.listByConditionWithWrapper(page, userDTO);
    }

}

