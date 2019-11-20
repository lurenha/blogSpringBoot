package com.peng.domain.JsonResult;

public class JsonResult {
    private Integer code;
    private String msg;
    private Object data;
    public JsonResult(){}
    public JsonResult(Integer code, String msg, Object obj){
        this.code=code;
        this.msg=msg;
        this.data=obj;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data + '}';
    }
}
