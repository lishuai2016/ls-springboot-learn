package com.ls.demo;

import com.ls.demo.controller.HelloWorldController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/7 20:04
 */
public class HelloWorldControllerTest {

    @Test
    public void testSayHello() {
        assertEquals("Hello,World!",new HelloWorldController().helloworld("Hello,World!"));
    }
}
