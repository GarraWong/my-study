package com.wong.testdemo;

import com.wong.testdemo.config.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类注释 完善它
 *
 * @author : wangyumou
 * @date : 2021/9/23 16:26
 */
@SpringBootApplication
public class Testdemo implements CommandLineRunner {

    @Autowired
    private MyProperties myProperties;


    public static void main(String[] args) {
        SpringApplication.run(Testdemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(myProperties.getAge());
        System.out.println(myProperties.getNumber());
    }
}
