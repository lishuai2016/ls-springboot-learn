package com.ls.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 10:59
 */
@Controller
public class FreemarkerController {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://www.jd.com");
        return "index";
    }
}
