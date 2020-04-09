package com.flowable.springboot.responseBean;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    //响应结果(100200为正确，其他值均为异常)
    private int code;
    //异常信息
    private String message;
    //数据信息
    private Object data;

    public BaseResponse(){
    }
    public BaseResponse(int code){
        this.code = code;
    }
    public BaseResponse(int code,Object data){
        this.code = code;
        this.data = data;
    }
    public BaseResponse(int code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

