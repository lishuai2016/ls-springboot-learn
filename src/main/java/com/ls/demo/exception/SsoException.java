package com.ls.demo.exception;

import java.io.Serializable;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 20:52
 */
public class SsoException extends Exception implements Serializable {

    private static final long serialVersionUID = 20160606L;

    private int code;

    public SsoException() {
        super();
    }

    public SsoException(String message) {
        super(message);
    }

    public SsoException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
