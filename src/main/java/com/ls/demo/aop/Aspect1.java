package com.ls.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/27 11:00
 */
@Component
@Scope
@Aspect
public class Aspect1 {

    @Pointcut("@annotation(com.ls.demo.aop.ServiceLimit)")
    public void Aspect1() {

    }

    @Before("Aspect1()")
    public void before(JoinPoint joinPoint) {
        System.out.println("[Aspect1] before advise");
    }

    @Around("Aspect1()")
    public void around(ProceedingJoinPoint pjp) throws  Throwable{
        System.out.println("[Aspect1] around advise before");
        pjp.proceed();
        System.out.println("[Aspect1] around advise after");
    }

    @AfterReturning("Aspect1()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterReturning advise");
    }

    @AfterThrowing("Aspect1()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterThrowing advise");
    }

    @After("Aspect1()")
    public void after(JoinPoint joinPoint) {
        System.out.println("[Aspect1] after advise");
    }





}
