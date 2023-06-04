package com.wong.testformac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 这是类的描述 补充它
 *
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/4/21 11:12
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(value = "com.wong.**.mapper", sqlSessionTemplateRef = "glyhSqlSessionTemplate")
public class TestForMac {

    public static void main(String[] args) {
        System.out.println("测试分支保护");
        SpringApplication.run(TestForMac.class, args);
    }


}
