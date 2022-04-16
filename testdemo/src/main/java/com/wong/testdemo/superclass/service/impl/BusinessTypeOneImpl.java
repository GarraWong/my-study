package com.wong.testdemo.superclass.service.impl;

import com.wong.testdemo.service.IPoiService;
import com.wong.testdemo.service.ValidatorService;
import com.wong.testdemo.superclass.service.BusinessTypeOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 这是类的描述 补充它
 *
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/4/15 14:12
 */
@Service
public class BusinessTypeOneImpl extends BaseServiceImpl implements BusinessTypeOne {

    @Autowired
    private ValidatorService validatorService;

    @Override
    public String typeOneTest() {
        String s = super.baseSelect(true);
        return s;
    }

    @Override
    public String typeOneTest2() {
        String s = super.baseInsert();
        return s;
    }

}
