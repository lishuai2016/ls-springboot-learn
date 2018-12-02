package com.ls.demo.interceptor;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/15 10:54
 */
@Component
public class InterceptorChain implements HandlerInterceptor, ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //存储所有的拦截器
    private LinkedList<HandlerInterceptor> handlerInterceptors;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.handlerInterceptors = Lists.newLinkedList();
        String[] beanNames = applicationContext.getBeanNamesForType(MyHandlerInterceptor.class); //根据类型来获取所有的实例对象
        List<HandlerInterceptor> orderhandlerInterceptors = Lists.newArrayList(); //有序的拦截器
        List<HandlerInterceptor> handlerInterceptors = Lists.newArrayList();  //无序的拦截器
        for (String bean :beanNames) {
            //通过bean的名字和类型从spring容器来获取实例对象
            HandlerInterceptor handlerInterceptor = applicationContext.getBean(bean, HandlerInterceptor.class);
            //判断一个类是否实现了什么接口
            if (handlerInterceptor != null && Ordered.class.isAssignableFrom(handlerInterceptor.getClass())) {
                orderhandlerInterceptors.add(handlerInterceptor);
            } else if (handlerInterceptor != null && !Ordered.class.isAssignableFrom(handlerInterceptor.getClass())) {
                handlerInterceptors.add(handlerInterceptor);
            }
        }
        Collections.sort(orderhandlerInterceptors, new Comparator<HandlerInterceptor>() {
            @Override
            public int compare(HandlerInterceptor o1, HandlerInterceptor o2) {
                Ordered ordered1 = (Ordered)o1;
                Ordered ordered2 = (Ordered)o2;
                return ordered1.getOrder() - ordered2.getOrder();
            }
        });
        this.handlerInterceptors.addAll(orderhandlerInterceptors);
        this.handlerInterceptors.addAll(handlerInterceptors);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        for (int i=0;i < this.handlerInterceptors.size();i++) {
            if (!this.handlerInterceptors.get(i).preHandle(request,response,handler)) {
                triggerAfterCompletion(this.handlerInterceptors.subList(0,i),request,response,handler,null);
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        for (int i=0;i < this.handlerInterceptors.size();i++) {
            this.handlerInterceptors.get(i).preHandle(request,response,handler);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        triggerAfterCompletion(this.handlerInterceptors,request,response,handler,ex);
    }


    private void triggerAfterCompletion(List<HandlerInterceptor> interceptors, HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
             {
        if (!ObjectUtils.isEmpty(interceptors)) {
            for (int i = interceptors.size();i>=0;i--) {
                HandlerInterceptor interceptor = interceptors.get(i);
                try {
                    interceptor.afterCompletion(request,response,handler,null);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("HandlerInterceptor.afterCompletion threw exception", e);
                }
            }
        }



    }
}
