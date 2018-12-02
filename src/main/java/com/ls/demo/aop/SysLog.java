package com.ls.demo.aop;

import java.lang.annotation.*;

/**
 * @program: ls-springboot
 * @author: lishuai
 * @create: 2018-11-27 21:08
 * 系统日志注解 加载controller的请求方法上，用来解析不同类型的操作
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
