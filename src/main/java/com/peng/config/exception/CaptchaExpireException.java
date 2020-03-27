package com.peng.config.exception;

/**
 * 验证码失效异常类
 */
public class CaptchaExpireException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException(String msg) {
        super(msg);
    }

}