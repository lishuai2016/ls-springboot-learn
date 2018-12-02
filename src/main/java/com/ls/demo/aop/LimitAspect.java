package com.ls.demo.aop;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/12 11:29
 *
 * 注意，通过aop切面的方式来对接口进行限流，只能加在方法上，加在类上和参数上均不走aop切面，相当于失效了
 *
 */
@Component
@Scope
@Aspect
public class LimitAspect {
    //每秒只发出5个令牌，此处是单进程服务的限流,内部采用令牌捅算法实现
    private static RateLimiter rateLimiter = RateLimiter.create(0.1);

    //Service层切点  限流
    @Pointcut("@annotation(com.ls.demo.aop.ServiceLimit)")
    public void ServiceAspect() {

    }

    @Around("ServiceAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        // 获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String methodName = joinPoint.getSignature().getName();
        Object result = null;
        Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        try {
            if(flag){
                System.out.println("【环绕通知中的--->前置通知】：the method 【" + methodName + "】 begins with " + Arrays.asList(joinPoint.getArgs()));
                obj = joinPoint.proceed();
                System.out.println("【环绕通知中的--->返回通知】：the method 【" + methodName + "】 ends with " + result);
            } else {
                return "限流了";
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("【环绕通知中的--->异常通知】：the method 【" + methodName + "】 occurs exception " + e);

        }
        System.out.println("【环绕通知中的--->后置通知】：-----------------end.----------------------");
        return obj;
    }


    @Before("ServiceAspect()")
    public void beforeMethod(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        System.out.println("【前置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
    }

    @AfterReturning(value="ServiceAspect()",returning="result")
    public void afterReturningMethod(JoinPoint jp, Object result){
        String methodName = jp.getSignature().getName();
        System.out.println("【返回通知】the method 【" + methodName + "】 ends with 【" + result + "】");
    }

    @After("ServiceAspect()")
    public void afterMethod(JoinPoint jp){
        System.out.println("【后置通知】this is a afterMethod advice...");
    }

    @AfterThrowing(value="ServiceAspect()",throwing="e")
    public void afterThorwingMethod(JoinPoint jp, NullPointerException e){
        String methodName = jp.getSignature().getName();
        System.out.println("【异常通知】the method 【" + methodName + "】 occurs exception: " + e);
    }



}
