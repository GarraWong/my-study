package com.wong.testdemo.superclass.service.impl;

import com.wong.testdemo.service.IPoiService;
import com.wong.testdemo.service.ValidatorService;
import com.wong.testdemo.superclass.service.BusinessTypeTwo;
import org.springframework.stereotype.Service;

/**
 * 这是类的描述 补充它
 *
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/4/15 17:23
 */
@Service
public class BusinessTypeTwoImpl extends BaseServiceImpl implements BusinessTypeTwo {

    @Override
    public String typeTwoTest() {
        String s = super.baseSelect(true);
        return s;
    }

    @Override
    public String typeTwoTest2() {
        String s = super.baseInsert();
        return s;
    }
}
