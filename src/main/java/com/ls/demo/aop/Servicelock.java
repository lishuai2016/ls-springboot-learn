package com.ls.demo.aop;

import java.lang.annotation.*;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/27 11:40
 *
 * 加在service层的方法上
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface Servicelock {
    String description()  default "";
}
