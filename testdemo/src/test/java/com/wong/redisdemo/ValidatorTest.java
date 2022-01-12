package com.wong.redisdemo;

import com.wong.testdemo.service.ValidatorService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 输入类描述
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 9:34
 */
@SpringBootTest
public class ValidatorTest {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorTest.class);

    @Autowired
    private ValidatorService validatorService;


    @Test
    public void onlyInterface() {
        String name = validatorService.getName("");
        logger.info("接口调用返回结果:{}", name);
    }
}
