package com.peng.aspect;

import com.peng.entity.RequestLog;
import com.peng.service.IRequestLogService;
import com.peng.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect// 使用@Aspect注解声明一个切面
@Component
public class MyLogAspect {

    @Autowired
    private IRequestLogService iRequestLogService;

    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Before("execution(public * com.peng.controller..*(..)) && @annotation(myLog)")
    public void doBefore(JoinPoint joinPoint,MyLog myLog) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI();
        String ipAddress = IpUtil.getIpAddress(request);
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsStr=Arrays.toString(args);
        if(argsStr.length()>=255){argsStr=argsStr.substring(0,245)+"......";}
        iRequestLogService.save(RequestLog.builder().url(url).ipAddress(ipAddress).classMethod(classMethod).args(argsStr).build());
    }




}
