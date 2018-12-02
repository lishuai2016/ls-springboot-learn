package com.ls.demo.controller;


import com.ls.demo.properties.BlogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 10:32
 */
@RestController
public class PropertiesController {

    @Autowired
    private BlogProperties blogProperties;
    //注意：通过@Component注入对象的时候，必须保证类所在的包在Application父包路径下，否则会出现找不到

    @RequestMapping("/properties")
    public String helloworld(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "properties"+blogProperties.toString();
    }
}
