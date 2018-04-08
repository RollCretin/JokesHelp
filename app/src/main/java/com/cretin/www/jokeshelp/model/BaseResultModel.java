package com.cretin.www.jokeshelp.model;

/**
 * Created by cretin on 2018/4/8.
 */

public class BaseResultModel<T> {
    // 返回数据通用message
    private String message = "数据返回成功";
    // 请求状态码 0 失败 1 成功 2 登录时效
    private int code = 1;
    // 返回数据
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
