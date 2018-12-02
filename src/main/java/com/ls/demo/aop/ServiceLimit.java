package com.ls.demo.aop;

import java.lang.annotation.*;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/12 11:30
 */
@Target({ElementType.PARAMETER, ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface ServiceLimit {
    String description()  default "限流注解";
}
