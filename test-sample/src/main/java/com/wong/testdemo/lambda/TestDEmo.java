package com.wong.testdemo.lambda;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/8/12 17:31
 */
public class TestDEmo {

    public static void main(String[] args) {
        String str = "tt";
        MyFunction myFunction = a ->{
            System.out.println(a.equals("aa"));
            System.out.println("执行了自定义逻辑");
            System.out.println("执行了我的逻辑");
        };
        testFunc(myFunction,str);
    }


    public static void testFunc(MyFunction myFunction, String str) {
        //一些逻辑
        System.out.println("开始了");
        myFunction.doSomeThing(str);
        System.out.println("执行完了");
        //一些后记逻辑
    }
}
