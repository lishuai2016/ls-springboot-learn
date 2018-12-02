package com.ls.demo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/7 20:21
 * 单点登录帮助工具
 */
public class SSOHelper {

    public static final String SSO_COOKIE_NAME = "bupt.ls.com";


    /**
     * 获取访问者真实IP地址
     *
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null) {
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            int position = ip.indexOf(",");
            if (position > 0) {
                ip = ip.substring(0, position);
            }
        }
        return ip;
    }


    /**
     * 删除单点登录凭证cookie:sso.jd.com
     *
     * @param response
     * @param domain
     */
    public static void logout(HttpServletResponse response, String domain) {
        Cookie cookie = new Cookie(SSO_COOKIE_NAME, null);
        if (domain != null && !"".equals(domain.trim())) {
            cookie.setDomain(domain);
        }
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }



    /**
     * 创建cookie，适用于整个domain，并且设置cookie的有效期
     * 其中cookie的声明周期，如果设置
     * 一个正数字，表示在多少秒后失效；
     * 一个负数表示有效期到浏览器关闭；
     * 0表示删除cookie；
     * 不设置代表默认在关闭浏览器后失效
     * @param response
     * @param name
     * @param value
     * @param domain
     */
    public static void setGlobalCookie(HttpServletResponse response, String name, String value, String domain) {
        setCookie(response, name, value, -1, domain);
    }

    private static void setCookie(HttpServletResponse response, String name, String value, int maxAge, String domain) {
        if (name == null) {
            return;
        }
        Cookie cookie = new Cookie(name, value);
        if (domain != null && !"".equals(domain.trim())) {
            cookie.setDomain(domain);
        }
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }


    public static String getCookieValue(HttpServletRequest servletRequest, String name) {
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
