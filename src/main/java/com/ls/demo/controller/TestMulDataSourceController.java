package com.ls.demo.controller;

import com.ls.demo.aop.ServiceLimit;
import com.ls.demo.domain.User;
import com.ls.demo.mybatisxml.UserXMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/20 20:51
 */
@RestController
public class TestMulDataSourceController {

    @Autowired
    private UserXMLMapper userXMLMapper;

    @RequestMapping("/data")
    public String helloworld( )  {
        List<User> all = userXMLMapper.findAll();
        System.out.println(all);
        System.out.println(all.size());

        return "helloworld";
    }
}
