package com.flowable.springboot.common;

public class ResponseBean {

    // http status code
    private int code;
    // return message
    private String message;
    // return data
    private Object data;
    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ResponseBean(int code, String msg){
        this(code, msg, null);
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String msg) {
        this.message = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}