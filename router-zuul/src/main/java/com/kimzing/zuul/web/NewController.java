package com.kimzing.zuul.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Zuul的url重定向.
 *
 * @author kim - kimzing@163.com
 * @since 2018/8/12 03:04
 */
@RestController
@RequestMapping("/new")
public class NewController {

    /**
     * 根据配置中的forward规则,访问/old/hello会跳转到/new/hello.
     *
     * @return java.lang.String
     */
    @GetMapping("/hello")
    public String getWord() {
        return "Hello World!";
    }

}
