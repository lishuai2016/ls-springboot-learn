package com.ls.demo.config;

import com.ls.demo.interceptor.InterceptorChain;
import com.ls.demo.interceptor.ParameterAnnotationInterceptor;
import com.ls.demo.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 14:01
 */
@Configuration
public class TestInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/test为前缀的 url路径
        registry.addInterceptor(new ParameterAnnotationInterceptor()).addPathPatterns("/helloworld");

    }



}
