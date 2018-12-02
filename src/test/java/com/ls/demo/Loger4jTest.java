package com.ls.demo;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * @Author: lishuai
 * @CreateDate: 2018/11/10 10:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Loger4jTest {
    private Logger logger = LoggerFactory.getLogger(Loger4jTest.class);

    @Test
    public void test() throws Exception {

        logger.debug("输出debug");
        logger.info("输出info");
        logger.warn("输出warn");
        logger.error("输出error11111");
    }
}
