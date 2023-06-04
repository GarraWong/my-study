package com.wong.testdemo.superclass.controller;

import com.wong.testdemo.superclass.service.BusinessTypeOne;
import com.wong.testdemo.superclass.service.BusinessTypeTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是类的描述 补充它
 *
 * @author: Wym's Code code in MacBook pro 2020 Silicon
 * @date: 2022/4/15 14:14
 */
@RestController
@RequestMapping("/superclass")
public class SuperClassController {

    @Autowired
    private BusinessTypeOne businessTypeOne;
    @Autowired
    private BusinessTypeTwo businessTypeTwo;

    @GetMapping("/useable")
    public String isUseable() {
        return businessTypeOne.typeOneTest();
    }

    @GetMapping("/useable2")
    public String isUseable2() {
        return businessTypeOne.typeOneTest2();
    }

    @GetMapping("/useable3")
    public String isUsable3() {
        return businessTypeOne.baseSelect(true);
    }

    @GetMapping("/useable4")
    public String isUsable4() {
        return businessTypeTwo.baseInsert();
    }


    @GetMapping("/useable5")
    public String isUseable5() {
        return businessTypeTwo.typeTwoTest();
    }

    @GetMapping("/useable6")
    public String isUseable6() {
        return businessTypeTwo.typeTwoTest2();
    }

    @GetMapping("/useable7")
    public String isUsable7() {
        return businessTypeTwo.baseSelect(true);
    }

    @GetMapping("/useable8")
    public String isUsable8() {
        return businessTypeTwo.baseInsert();
    }

    @FunctionalInterface
    public interface Person {
        public void sayHello (String s);
    }

    public class Hello {
        private void printHello (Person person) {
            String s = "Hello";
            person.sayHello(s);
        }
        private void printText () {
            printHello(s -> System.out.println(s));
        }
    }
}
