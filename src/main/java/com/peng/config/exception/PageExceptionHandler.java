package com.peng.config.exception;


import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/***
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class PageExceptionHandler {

    /***
     * 验证码认证异常
     */
    @ResponseBody
    @ExceptionHandler(CaptchaExpireException.class)
    public Object handleException(CaptchaExpireException e) {
        return ResultUtil.faile(ResultCode.CODE_AUTH_ERROR);
    }


    /***
     * 请求方式（get/post）异常
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleException(HttpRequestMethodNotSupportedException e) {
        return ResultUtil.faile(ResultCode.INTERFACE_METHOD_ERROR);
    }

    /**
     * AccessDeniedException
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public Object handleException(AccessDeniedException e) {
        return ResultUtil.faile(ResultCode.PERMISSION_NO_ACCESS);
    }


    /***
     * 其他异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        e.printStackTrace();
        return new JsonResult(50000, "服务器开小差了", null);
    }
}
