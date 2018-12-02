package com.ls.demo.controller;

import com.ls.demo.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 13:19
 * 测试异常
 *
 */
@Controller
public class ExceptionController {
    @RequestMapping("/exception/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/exception/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

    @RequestMapping("/exception")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }
}
