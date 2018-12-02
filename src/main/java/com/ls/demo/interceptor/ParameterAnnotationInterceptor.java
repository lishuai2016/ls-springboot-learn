package com.ls.demo.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.ls.demo.aop.ServiceLimit;
import com.ls.demo.common.ApiStatus;
import com.ls.demo.common.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Parameter;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/16 18:20
 * 在拦截器上进行限流：通过对参数加注解
 */
public class ParameterAnnotationInterceptor implements HandlerInterceptor {
    //每秒只发出5个令牌，此处是单进程服务的限流,内部采用令牌捅算法实现
    private static RateLimiter rateLimiter = RateLimiter.create(0.1);

    private static Logger logger = LoggerFactory.getLogger(ParameterAnnotationInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            MethodParameter[] parameters = ((HandlerMethod) handler).getMethodParameters();
            for (MethodParameter parameter :parameters) {
                Parameter p = parameter.getParameter();//参数注解
                boolean annotationPresent = p.isAnnotationPresent(ServiceLimit.class);
                if (annotationPresent) {
                    ServiceLimit annotation = p.getAnnotation(ServiceLimit.class);
                    System.out.println("description=" +annotation.description());
                }
                Boolean flag = rateLimiter.tryAcquire();
                if (annotationPresent && !flag && request.getParameter("type").equals("1")) {
                    System.out.println("访问频繁进行限流处理");
                    writeJsonApiStatusResponse(Status.SECURITY_REQUEST_TOO_FREQUENCY,response);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

    /**
     * 通过response对象把拦截器处理的结果返回给请求端
     * @param apiStatus
     * @param response
     */
    private void writeJsonApiStatusResponse(ApiStatus apiStatus, HttpServletResponse response) {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            response.getWriter().write(JSON.toJSONString(apiStatus));
        } catch (IOException e) {
            logger.error("写api状态json失败!!!", e);
        }
    }
}
