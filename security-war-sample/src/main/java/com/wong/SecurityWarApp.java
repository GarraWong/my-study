package com.wong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/4/12 19:36
 */
@SpringBootApplication
public class SecurityWarApp {

    private static final Logger logger = LoggerFactory.getLogger(SecurityWarApp.class);

    public static void main(String[] args) {
        logger.info("执行普通标准启动类...");
        SpringApplication.run(SecurityWarApp.class);
    }

}
