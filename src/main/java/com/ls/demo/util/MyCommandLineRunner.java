package com.ls.demo.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/13 23:33
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(args);
        System.out.println("测试CommandLineRunner接口");
    }
}
