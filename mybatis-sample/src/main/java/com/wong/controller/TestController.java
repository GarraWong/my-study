package com.wong.controller;

import com.alibaba.fastjson2.JSONObject;
import com.wong.domain.Dept;
import com.wong.mapper.MyInsertMapper;
import com.wong.service.MyInsertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/8/3 21:08
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    MyInsertService myInsertService;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping("/wrong")
    public String test() {
        logger.debug("进入测试方法");
        myInsertService.insert();
        return "成功";
    }

    @PostMapping("/map")
    public String mapParam(JSONObject map) {
        Object file = map.get("file");
        return "成功";
    }
}
