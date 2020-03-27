package com.peng.entity.Result;

public class ResultUtil {
    //成功，不返回具体数据
    public static <T> JsonResult<T> successNoData(ResultCode code){
        JsonResult<T> result = new JsonResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }
    //成功，返回数据
    public static <T> JsonResult<T> success(T t,ResultCode code){
        JsonResult<T> result = new JsonResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }

    //失败，返回失败信息
    public static <T> JsonResult<T> faile(ResultCode code){
        JsonResult<T> result = new JsonResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }

    //失败，返回失败信息
    public static <T> JsonResult<T> faileAndData(T t,ResultCode code){
        JsonResult<T> result = new JsonResult<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }

}
