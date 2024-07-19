package com.wong.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2024/6/25 16:11
 */
@RestController
@RequestMapping("/sentinel")
public class SentinelController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/flow")
    public String testFlow() {

        logger.info("进入接口时间 {}", DateUtil.now());
        // 被保护的逻辑
        return "成功";
    }

    @GetMapping("/rule")
    public String getRules() {
        return JSONArray.toJSONString(FlowRuleManager.getRules());
    }
}
