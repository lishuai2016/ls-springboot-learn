package com.ls.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/12 17:54
 */
@Controller
@RequestMapping
public class TestController {

    @RequestMapping(value = "/ajax")
    public String tologin(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
        return "ajax";
    }
}
