package com.example.bicyclestore.base;

public enum ResultCode {
    SUCCESS(200,"success"),
    FAIL(202,"fall"),
    NOT_FOUND(404,"not found")
    ;

    private Integer code;
    private String msg;

    ResultCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}