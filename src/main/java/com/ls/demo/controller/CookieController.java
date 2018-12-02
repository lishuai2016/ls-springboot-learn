package com.ls.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 9:58
 */
@RestController
public class CookieController {
    //访问计数器 保证全局唯一
    private static final AtomicInteger counter = new AtomicInteger();

    //测试cookie的性质
    @RequestMapping("/test_cookie")
    public String test_cookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("当前访问是第"+counter.get()+"次访问");
        Cookie[] cookies = request.getCookies();
        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies!=null) {
            System.out.println("当前请求中的cookie数组大小："+cookies.length);
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("lastAccessTime")) {
                    Long lastAccessTime =Long.parseLong(cookie.getValue());
                    System.out.println("上一个lastAccessTime的cookie值："+lastAccessTime);
                }
            }
        } else {
            System.out.println("当前请求中的cookie数组为null");
        }
        //用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis()+"");//创建一个cookie，cookie的名字是lastAccessTime
        // 其中cookie的声明周期，如果设置一个正数字，表示在多少秒后失效；一个负数表示有效期到浏览器关闭；0表示删除cookie；不设置代表默认在关闭浏览器后失效
        //cookie.setMaxAge(-1);
        // 将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
        //cookie.setDomain("");设置cookie的有效域
        response.addCookie(cookie);
        return "当前访问是第"+counter.getAndIncrement()+"次访问"+"当前时间;"+ new Date();
    }
}
