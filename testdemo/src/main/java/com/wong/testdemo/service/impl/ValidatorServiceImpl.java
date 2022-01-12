package com.wong.testdemo.service.impl;

import com.wong.testdemo.model.PayVo;
import com.wong.testdemo.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/9/26 9:21
 */
@Service
public class ValidatorServiceImpl implements ValidatorService {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorServiceImpl.class);

    @Override
    public String getName(String name) {
        logger.info("方法内接收到的入参是:{}", name);
        return "name=" + name;
    }

    @Override
    public String pay(PayVo vo) {
        return vo.toString();
    }

}
