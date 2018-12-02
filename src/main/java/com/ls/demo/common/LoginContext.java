package com.ls.demo.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 20:41
 */
public class LoginContext {
    private final static ThreadLocal<LoginContext> holder = new ThreadLocal<LoginContext>();

    private final static Log log = LogFactory.getLog(LoginContext.class);

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 登录名称
     */
    private String username;

    /**
     * 实际上是将loginContext放到了actionContext中
     *
     * @param loginContext 对象
     */
    public static void setLoginContext(LoginContext loginContext) {
        holder.set(loginContext);
    }

    /**
     * 取出登录的上下文
     *
     * @return null 如果没有的话
     */
    public static LoginContext getLoginContext() {
        return holder.get();
    }

    /**
     * 删除上下文、其实一般不用删除
     */
    public static void remove() {
        holder.remove();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
