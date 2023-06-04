//package com.wong;
//
//import com.wong.testformac.TestForMac;
//import com.wong.testformac.service.TransactionTestService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * 这是类的描述 补充它
// *
// * @author : Wym's Code code in MacBook pro 2020 Silicon
// * @date : 2022/9/29 12:00
// */
//@SpringBootTest(classes = TestForMac.class)
//@RunWith(SpringRunner.class)
//public class DynamicDataSourceTest {
//
//    @Autowired
//    private TransactionTestService transactionTestService;
//
//    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceTest.class);
//
//    @Test
//    public void testTransaction() {
//        logger.info("开始测试");
//        transactionTestService.insertFail();
//        logger.info("测试结束");
//    }
//
//    @Test
//    public void testTransaction2() {
//        transactionTestService.insertSuccess();
//    }
//
//    @Test
//    public void testTransactionOfSqlException() {
//        transactionTestService.insertFailOfSqlException();
//    }
//}
