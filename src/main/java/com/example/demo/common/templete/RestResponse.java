package com.example.demo.common.templete;

import org.springframework.http.HttpStatus;

public class RestResponse {
    private HttpStatus code;
    private String msg;
    private Object data;

    public RestResponse(HttpStatus code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RestResponse ok(Object data) {
        return new RestResponse(HttpStatus.OK, "SUCCESS", data);
    }

    public static RestResponse error(String msg) {
        return new RestResponse(HttpStatus.BAD_REQUEST, msg, null);
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
