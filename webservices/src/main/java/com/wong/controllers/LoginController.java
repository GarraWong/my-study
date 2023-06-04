package com.wong.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/6/4 15:27
 */
@RestController
public class LoginController {


    @GetMapping("/login")
    public String login() {
        return "成功";

    }

}
