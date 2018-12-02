package com.ls.demo.annotation;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/16 17:04
 */
public class Main {
    public static void main(String[] args) {
        //解析注解 获得我们需要解析注解的类
        Class<Test> clz = Test.class;
        //解析Class 由于我们的注解是可以给类使用的,所以首先判断类上面有没有我们的注解  判断类上面是否有注解
        boolean clzHasAnnotation = clz.isAnnotationPresent(TestAnnotation.class);
        if (clzHasAnnotation) {
            //类存在我们定义的注解  获得注解
            TestAnnotation clzAnnotation = clz.getAnnotation(TestAnnotation.class);
            //输出注解在类上的属性
            System.out.println("name=" + clzAnnotation.name() + "\tage=" + clzAnnotation.age());
        }
        //解析Method  获得类中所有方法
        Method[] methods = clz.getMethods();
        for (Method m : methods) {
            //获得方法中是否含有我们的注解
            boolean methodHasAnnotation = m.isAnnotationPresent(TestAnnotation.class);
            if (methodHasAnnotation) {
                //注解存在 获得注解
                TestAnnotation methodAnnotation = m.getAnnotation(TestAnnotation.class);
                System.out.println("name=" + methodAnnotation.name() + "\tage=" + methodAnnotation.age());
                System.out.println("获取方法上参数的注解");
                Parameter[] parameters = m.getParameters();
                for (int i = 0;i < parameters.length;i++) {
                    Parameter parameter = parameters[i];
                    if (parameter.isAnnotationPresent(TestAnnotation.class)) {
                        TestAnnotation parameterAnnotation = parameter.getAnnotation(TestAnnotation.class);
                        System.out.println("name=" + parameterAnnotation.name() + "\tage=" + parameterAnnotation.age());
                    }
                }
            }

        }
    }
 }
