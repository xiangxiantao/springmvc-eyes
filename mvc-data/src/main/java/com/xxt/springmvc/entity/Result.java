package com.xxt.springmvc.entity;


public class Result<T> {

    /**
     * 响应状态
     */
    private Boolean ret;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 相应数据
     */
    private T data;

    public Result(T data) {
        this.ret = true;
        this.data = data;
    }

    public Result(String code, String message) {
        this.ret = false;
        this.code = code;
        this.message = message;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
