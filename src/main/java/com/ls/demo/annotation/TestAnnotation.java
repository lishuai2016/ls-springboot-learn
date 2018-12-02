package com.ls.demo.annotation;

import java.lang.annotation.*;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/16 17:02
 */
@Documented
@Inherited
//该注解可以作用于方法,类与接口
@Target({ElementType.METHOD, ElementType.TYPE,ElementType.PARAMETER})
//JVM会读取注解,所以利用反射可以获得注解
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    //定义成员变量
    //成员变量可以通过default指定默认值
    //如果成员变量不指定默认值的情况下
    //我们在引用接口时则必须给没有默认值的成员变量赋值
    String name() ;
    int age() default 18 ;
}

