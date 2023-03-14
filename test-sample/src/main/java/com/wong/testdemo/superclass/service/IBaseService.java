package com.wong.testdemo.superclass.service;

import org.springframework.validation.annotation.Validated;

/**
 * 父类接口
 *
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/4/15 14:05
 */
@Validated
public interface IBaseService {


    String baseSelect(boolean flag);

    String baseInsert();

}
