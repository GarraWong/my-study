package com.wang.openfeign.controller;

import cn.hutool.core.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * 这是被调用的模块
 * 这个模块里的方法 也可以被前端直接调用 也可以通过feign被其他模块调用
 *
 * @author : wangyumou's Dell inspiration
 * @date : 2021/12/20 16:32
 */
@RestController
@RequestMapping("/provider")
@CrossOrigin
public class BusinessController {

    private static final String baseString = "曾经富丽堂皇的古行宫已是一片荒凉冷落幸存的几个满头白发的宫女闲坐无事只能谈论着玄宗轶事";

    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);



    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") Integer id) {
        logger.info("接收到入参:{}", id);
        //TODO 假设查询数据库
        String name = RandomUtil.randomString(baseString, 3);
        logger.info("我生成的用户名:{}", name);
        return "查询用户结果是:" + name;
    }
}
