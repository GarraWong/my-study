package com.wong.testdemo.superclass.service.impl;

import com.wong.testdemo.service.IPoiService;
import com.wong.testdemo.service.ValidatorService;
import com.wong.testdemo.superclass.service.IBaseService;
import org.docx4j.openpackaging.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 父类业务的骨架类
 *
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/4/15 14:07
 */
public abstract class BaseServiceImpl implements IBaseService {

    @Autowired
    private  ValidatorService validatorService;
    @Autowired
    private  IPoiService poiService;

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Override
    public String baseSelect(boolean flag) {
        String name = validatorService.getName(String.valueOf(flag));
        poiService.docxToHtml("aaa");
        logger.debug("baseSelect:{}", name);
        return "baseSelect:" + validatorService.toString() + poiService.toString();
    }

    @Override
    public final String baseInsert() {
        logger.debug("baseInsert==>this : {}", this);
        if (validatorService == null || poiService == null) {
            logger.error("baseInsert,为空");
            return "为空";
        } else {
            logger.debug("baseInsert,不为空 . validator:{} , poiservice : {}", validatorService, poiService);
            return "baseInsert:" + validatorService.toString() + poiService.toString();
        }

    }
}
