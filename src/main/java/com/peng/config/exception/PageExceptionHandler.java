package com.peng.config.exception;


import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;


/***
 * 全局异常处理
 */
@ControllerAdvice
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
     * Token认证异常
     */
    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public Object handleException(AuthenticationException e) {
        return ResultUtil.faile(ResultCode.Token_AUTH_ERROR);
    }

    /***
     * 权限异常
     */
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public Object handleException(UnauthorizedException e) {
        return ResultUtil.faile(ResultCode.PERMISSION_NO_ACCESS);
    }

    /***
     * 请求方式（get/post）异常
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleException(HttpRequestMethodNotSupportedException e) {
        return ResultUtil.faile(ResultCode.INTERFACE_METHOD_ERROR);
    }

    /***
     * 其他异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        // 记录错误信息
        String msg = e.getMessage();
        if (Strings.isBlank(msg)) {
            msg = "服务器出错";
        }
        return new JsonResult(50000, msg, null);
    }
}
