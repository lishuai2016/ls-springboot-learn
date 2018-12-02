package com.ls.learn.test;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/17 12:54
 *
 * 抽象类继承接口的时候可以不实现接口定义的方法，并且抽象类内部的
 *
 */
public abstract class MyAbstractClass implements MyInterface {

    public static String NAME="zhangsan";
    public String hello() {
        return "hello";
    }



}
