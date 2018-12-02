package com.ls.demo.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/16 18:35
 */
public class ApiStatus<E> {

    // 这里的int类型数据是用来填充statusCode的，OK 代表正常，APPEXCEPTION代表应用级别异常，SYSTEMEXCEPTION用于系统级别异常供抛出未捕获异常用。
    public static final Integer OK = 200;
    public static final Integer APPEXCEPTION = 300;
    public static final Integer SYSTEMEXCEPTION = 500;

    public static final Integer REQUEST_LIMIT = 444;    // 限流状态码
    private static final ApiStatus DEFAULT_OK = new ApiStatus();
    private static final ApiStatus DEFAULT_ERROR = new ApiStatus();

    static {
        DEFAULT_OK.setResult(true);
        DEFAULT_OK.setStatusCode(OK);
        DEFAULT_OK.setMessage("ok");

        DEFAULT_ERROR.setResult(false);
        DEFAULT_ERROR.setStatusCode(SYSTEMEXCEPTION);
        DEFAULT_ERROR.setMessage("error");
    }


    private boolean result;
    private int statusCode;
    private String message;
    private E info;

    public static ApiStatus newOkApi() {
        return DEFAULT_OK;
    }

    public static <T> ApiStatus<T> newOkApi(T info) {
        ApiStatus<T> apiStatus = new ApiStatus<T>();
        apiStatus.setResult(true);
        apiStatus.setStatusCode(OK);
        apiStatus.setMessage("ok");
        apiStatus.setInfo(info);
        return apiStatus;
    }

    public static <T> ApiStatus<T> newOkApi(String message, T info) {
        ApiStatus<T> apiStatus = new ApiStatus<T>();
        apiStatus.setResult(true);
        apiStatus.setStatusCode(OK);
        apiStatus.setMessage(message);
        apiStatus.setInfo(info);
        return apiStatus;
    }


    public static ApiStatus newErrorApi() {
        return DEFAULT_ERROR;
    }

    public static <T> ApiStatus<T> newErrorApi(T info) {
        ApiStatus<T> apiStatus = new ApiStatus<T>();
        apiStatus.setResult(false);
        apiStatus.setStatusCode(SYSTEMEXCEPTION);
        apiStatus.setMessage("error");
        apiStatus.setInfo(info);
        return apiStatus;
    }

    public static <T> ApiStatus<T> newErrorApi(String message, T info) {
        ApiStatus<T> apiStatus = new ApiStatus<T>();
        apiStatus.setResult(false);
        apiStatus.setStatusCode(SYSTEMEXCEPTION);
        apiStatus.setMessage(message);
        apiStatus.setInfo(info);
        return apiStatus;
    }

    public static <T> ApiStatus<T> newErrorApi(Integer statusCode, String message, T info) {
        ApiStatus<T> apiStatus = new ApiStatus<T>();
        apiStatus.setResult(false);
        apiStatus.setStatusCode(statusCode);
        apiStatus.setMessage(message);
        apiStatus.setInfo(info);
        return apiStatus;
    }

    public static <T> ApiStatus<T> newErrorApi(Integer statusCode, String message) {
        ApiStatus<T> apiStatus = new ApiStatus<T>();
        apiStatus.setResult(false);
        apiStatus.setStatusCode(statusCode);
        apiStatus.setMessage(message);
        return apiStatus;
    }


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            public void run() {
                System.out.println(1);
            }
        });
    }
}
