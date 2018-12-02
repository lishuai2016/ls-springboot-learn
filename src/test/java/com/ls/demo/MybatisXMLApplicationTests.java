//package com.ls.demo;
//
//import com.ls.demo.domain.User;
//import com.ls.demo.mybatis.UserMapper;
//import com.ls.demo.mybatisxml.UserXMLMapper;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * @Author: lishuai
// * @CreateDate: 2018/11/9 13:29
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional     //开始事务会造成数据存不到数据库，程序运行结束数据库依旧没内容
////@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@MapperScan("com.ls.demo.mybatisxml")
//public class MybatisXMLApplicationTests {
//
//    @Autowired
//    private UserXMLMapper userMapper;   //提示找不到，不用管
//
//    @Test
//    @Rollback
//    public void findByName() throws Exception {
//
//        User u = userMapper.findByName("bbb");
//        System.out.println(u);
//        Assert.assertEquals(100, u.getAge().intValue());
//    }
//}
