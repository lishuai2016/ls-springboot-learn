package com.ls.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 10:02
 */
@Controller   // @RestController = @Controller + @ResponseBody
@RequestMapping("/session")
public class SessionController {
    private static final Map<String,String> usermap = new ConcurrentHashMap<>();

    static {
        usermap.put("lili","111111");
        usermap.put("xiaoming","222222");
    }

    @RequestMapping("/index")
    public String index(ModelMap map,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); //false 加不加都一样
        if (session == null) {
            System.out.println("说明用户没有登录去登陆");
        }
        String name = (String)session.getAttribute("loginName");
        if (name == null) {
            System.out.println("说明用户没有登录去登陆");
        }
        map.addAttribute("host", "http://www.jd.com");
        return "index";
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(ModelMap map,HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        if (usermap.containsKey(name) &&(usermap.get(name).equals(pwd))){
            //创建session对象
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("loginName", name);
            System.out.println("用户登录成功,跳转得首页");
            return "success";
        } else {
            System.out.println("用户登录失败，跳转到登录页面");
            map.addAttribute("message","登录失败");
            return "login";
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String tologin(ModelMap map,HttpServletRequest request, HttpServletResponse response) {
       return "login";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(ModelMap map,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        //有session并且登录
        if (session != null && session.getAttribute("name") != null) {
            request.getSession().invalidate();  //注销session
        }
        return "login";
    }


}
