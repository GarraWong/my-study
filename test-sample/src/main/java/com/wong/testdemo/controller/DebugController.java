package com.wong.testdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/11/17 11:09
 */
@RestController
@RequestMapping("/debug")
public class DebugController {

    private static final Logger logger = LoggerFactory.getLogger(DebugController.class);

    @GetMapping("/test")
    public String debugTest() {
        logger.info("进入方法");
        byte[] bytes = new byte[1024 * 1024 * 1024 * 6];
        byte[] bytes1 = new byte[1024 * 1024 * 1024 * 6];
        byte[] bytes2 = new byte[1024 * 1024 * 1024 * 6];
        byte[] bytes3 = new byte[1024 * 1024 * 1024 * 6];

        return "success";
    }

}
