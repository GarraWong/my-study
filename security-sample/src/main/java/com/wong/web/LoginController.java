package com.wong.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jitwxs
 * @date 2018/3/30 1:30
 */
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);


    @GetMapping("/login")
    public AjaxResult showLogin(@RequestParam("id_token") String token) {
        return AjaxResult.success(SecurityContextHolder.getContext().getAuthentication());
    }


    @RequestMapping("/other/1")
    public AjaxResult sms() {
        return AjaxResult.success(SecurityContextHolder.getContext().getAuthentication());
    }
}