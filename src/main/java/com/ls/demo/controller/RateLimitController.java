package com.ls.demo.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 9:59
 */
@RestController
public class RateLimitController {

    RateLimiter rateLimiter = RateLimiter.create(0.1); //每秒钟限流10次访问
    @RequestMapping("/rate_limit")
    public String rate_limit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("等待时间" + rateLimiter.acquire());//当超过限流时等待，直到拿到令牌
        return "rate_limit";
    }

    /**
     * tryAcquire(long timeout, TimeUnit unit)
     * 从RateLimiter 获取许可如果该许可可以在不超过timeout的时间内获取得到的话，
     * 或者如果无法在timeout 过期之前获取得到许可的话，那么立即返回false（无需等待）
     */
    @RequestMapping("/miaosha")
    public Object miaosha() {
        //判断能否在1秒内得到令牌，如果不能则立即返回false，不会阻塞程序
        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            System.out.println("短期无法获取令牌，真不幸，排队也瞎排");
            return "失败";
        } else {
            System.out.println("获取令牌成功");
            return "成功";
        }
    }
}
