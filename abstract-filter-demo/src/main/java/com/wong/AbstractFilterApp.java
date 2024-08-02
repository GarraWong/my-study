package com.wong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AbstractFilterApp {

    private static final Logger logger = LoggerFactory.getLogger(AbstractFilterApp.class);

    public static void main(String[] args) {
        SpringApplication.run(AbstractFilterApp.class);
        logger.info("执行普通标准启动类...");

    }

}