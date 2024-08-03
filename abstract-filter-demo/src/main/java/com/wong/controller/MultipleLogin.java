package com.wong.controller;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 测试SA-token登录集成
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2024/8/2 21:27
 */
@RestController
@RequestMapping("/login")

public class MultipleLogin {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/1")
    public SaTokenInfo get1() {
        logger.info("触发了:{}", DateUtil.date());
        StpUtil.login(1,SaLoginModel.create()
                        .setDevice("IOS")
                        .setExtra("id",1)
                        .setExtra("other","额外信息ios1")
                        .build()
                );
        return StpUtil.getTokenInfo();
    }

    @GetMapping("/get1")
    public SaTokenInfo getInfoBy1() {
        StpUtil.checkLogin();
        return StpUtil.getTokenInfo();
    }


    @GetMapping("/11")
    public SaTokenInfo get11() {
        logger.info("触发了:{}", DateUtil.date());
        StpUtil.login(11,SaLoginModel.create()
                .setDevice("IOS")
                .setExtra("id",11)
                .setExtra("other","额外信息ios11")
                .build()
        );
        return StpUtil.getTokenInfo();
    }

    @GetMapping("/2")
    public SaTokenInfo get2() {
        logger.info("触发了:{}", DateUtil.date());
        StpUtil.login(1,SaLoginModel.create()
                .setDevice("PC")
                .setExtra("id",1)
                .setExtra("other","额外信息PC")
                .build()
        );
        return StpUtil.getTokenInfo();
    }

    @GetMapping("/3")
    public SaTokenInfo get3() {
        logger.info("触发了:{}", DateUtil.date());
        StpUtil.login(1,SaLoginModel.create()
                .setDevice("PC")
                .setExtra("id",1)
                .setExtra("other","额外信息APP")
                .build()
        );
        return StpUtil.getTokenInfo();
    }

}
