package com.kingboy.provider.web.test;

import com.kingboy.provider.common.utils.exception.ExceptionManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 异常测试.
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    ExceptionManager exceptionManager;

    @ApiOperation(value = "抛出系统异常", tags = "异常")
    @GetMapping(value = "/system")
    public void testSystemException() {
        if (true) {
            throw new RuntimeException("系统异常");
        }
    }

    @ApiOperation(value = "抛出自定义异常", tags = "异常")
    @GetMapping(value = "/custom")
    public void testCustomException() {
        throw exceptionManager.create("PROV_0001");
    }

}
