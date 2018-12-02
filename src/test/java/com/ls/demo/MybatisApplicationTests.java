//package com.ls.demo;
//
//import com.ls.demo.domain.User;
//import com.ls.demo.mybatis.UserMapper;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
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
//public class MybatisApplicationTests {
//
//    @Autowired
//    private UserMapper userMapper;   //提示找不到，不用管
//
//
//    @Test
//    @Rollback
//    public void findByName() throws Exception {
//        userMapper.insert(3,"bbb", 20);
//        User u = userMapper.findByName("bbb");
//        System.out.println(u);
//        Assert.assertEquals(20, u.getAge().intValue());
//    }
//}
