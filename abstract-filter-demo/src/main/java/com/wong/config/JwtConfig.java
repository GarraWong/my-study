package com.wong.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2024/8/2 21:45
 */
@Configuration
public class JwtConfig {



    // Sa-Token 整合 jwt (Simple 简单模式)
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }


    // Sa-Token 参数配置，参考文档：https://sa-token.cc
    // 此配置会与 application.yml 中的配置合并 （代码配置优先）
    @Autowired
    public void configSaToken(SaTokenConfig config) {
        config.setTokenName("base-user-info");             // token 名称（同时也是 cookie 名称）
    }

}
