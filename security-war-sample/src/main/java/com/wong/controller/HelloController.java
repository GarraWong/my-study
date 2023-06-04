package com.wong.controller;

import com.wong.entity.AjaxResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/4/12 20:33
 */
@RestController
@CrossOrigin
public class HelloController {

    @GetMapping("/hello")
    public String get() {
        return "hello wym";
    }

    @GetMapping("/relogin")
    public AjaxResult error() {
        return AjaxResult.error(700, "请重新登录");
    }


}
