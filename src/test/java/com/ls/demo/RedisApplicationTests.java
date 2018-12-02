//package com.ls.demo;
//
//import com.ls.demo.domain.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @Author: lishuai
// * @CreateDate: 2018/11/9 11:46
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RedisApplicationTests {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    public void redisTest() {
//        // redis存储数据
//        String key = "name";
//        redisTemplate.opsForValue().set(key, "yukong");
//        // 获取数据
//        String value = (String) redisTemplate.opsForValue().get(key);
//        System.out.println("获取缓存中key为" + key + "的值为：" + value);
//
//        User user = new User();
//        user.setUsername("yukong");
//        user.setId(1L);
//        String userKey = "yukong";
//        redisTemplate.opsForValue().set(userKey, user);
//        User newUser = (User) redisTemplate.opsForValue().get(userKey);
//        System.out.println("获取缓存中key为" + userKey + "的值为：" + newUser);
//
//    }
//
//
//}
