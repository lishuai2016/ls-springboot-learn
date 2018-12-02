package com.ls.demo.interceptor;

import com.ls.demo.common.LoginContext;
import com.ls.demo.exception.SsoException;

import com.ls.demo.util.SSOHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 20:40
 */
public class SSOInterceptor implements HandlerInterceptor {

    private final static Log log = LogFactory.getLog(SSOInterceptor.class);
    /**
     * 单点登录cookie名称
     */
    private final static String SO_COOKIE_NAME = "sso.ls.com";
    /**
     * 单点登录的临时service ticket名称,默认30秒内有效
     */
    public static final String SSO_SERVICE_TICKET_NAME = "sso_service_ticket";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }


    private LoginContext getLoginContext(HttpServletRequest request, HttpServletResponse response) throws SsoException {
        LoginContext context = null;
        //获取cookie中的票据
        String ticket = SSOHelper.getCookieValue(request, SSOInterceptor.SO_COOKIE_NAME);
        if (ticket != null) {
            //验证票据的有效性



        } else {
            //没有票据说明还没登录或者登录了还没保存，再次获取登录后的临时票据
            String serviceTicket = request.getParameter(SSOInterceptor.SSO_SERVICE_TICKET_NAME);

        }

        return context;
    }

}
