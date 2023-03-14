package com.wong.testformac.service;

/**
 * 这是接口的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/9/29 12:14
 */
public interface TransactionTestService {

    int insertFail();

    int insertSuccess();

    int insertFailOfSqlException();
}
