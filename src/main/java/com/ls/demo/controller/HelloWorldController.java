package com.ls.demo.controller;

import com.ls.demo.aop.ServiceLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author: lishuai
 * @CreateDate: 2018/11/7 19:56
 */
@RestController
public class HelloWorldController {


    @RequestMapping("/helloworld")
    public String helloworld(@ServiceLimit String type)  {
        return "helloworld";
    }

//    @ServiceLimit
//    @RequestMapping("/helloworld/ls")
//    public String helloworld1( )  {
//        return "helloworld1";
//    }


    @ServiceLimit
    @RequestMapping("/helloworld/ls")
    public String test( @RequestParam boolean throwException ) throws Exception {
        if (throwException) {
            System.out.println("throw an exception");
            throw new Exception("mock a server exception");
        }
        System.out.println("test OK");
        return "helloworld1";
    }



}
