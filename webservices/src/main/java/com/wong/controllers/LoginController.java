package com.wong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/6/4 15:27
 */
@Controller
public class LoginController {


    @GetMapping("/login")
    @ResponseBody
    public String login() {
        System.out.println("进来了");
        return "成功";

    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("登出了");
        session.invalidate();
        return "redirect:http://localhost:8081/cas/logout";
    }

}
