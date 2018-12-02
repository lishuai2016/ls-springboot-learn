package com.ls.demo.interceptor;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/15 10:58
 */
public interface Ordered {
    int HIGHEST_PRECEDENCE = -2147483648;
    int LOWEST_PRECEDENCE = 2147483647;

    int getOrder();
}
