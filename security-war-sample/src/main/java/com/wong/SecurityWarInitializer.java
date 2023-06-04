package com.wong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class SecurityWarInitializer extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(SecurityWarInitializer.class);


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("执行启动配置");
        return application.sources(SecurityWarApp.class);
    }
}