package com.ls.demo.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 14:02
 */
public class TestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL().toString());
        System.out.println("访问了test下url路径。");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//                if (request.getRequestURI() != null) {
//                    System.out.println("当前请求的URI："+request.getRequestURI());
//                    if (request.getRequestURI().startsWith("/session/login")) {
//                        modelAndView.setViewName("redirect");
//                    }
//                }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
