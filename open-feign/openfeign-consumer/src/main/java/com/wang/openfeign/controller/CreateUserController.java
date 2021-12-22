package com.wang.openfeign.controller;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.wang.openfeign.api.RemoteProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 模拟创建用户的请求
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2021/12/20 16:46
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class CreateUserController {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserController.class);

    @Autowired
    private RemoteProviderService remoteProviderService;

    @GetMapping("/create")
    public String createUser(@RequestParam Integer id) {
        logger.info("前端传的数据是:{}", id);
        String user = remoteProviderService.getUser(id);
        logger.info("调用其他模块得到的结果是:\r{}", user);
        //TODO 做业务处理
        return user;
    }

}
