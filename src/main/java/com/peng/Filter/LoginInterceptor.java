package com.peng.Filter;

import com.peng.domain.JsonResult.JsonResult;
import com.peng.util.TokenUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *访问后台页面时验证token
 * */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Peng-Token");
        if(token!=null){
            if(TokenUtil.verify(token)){
                System.out.println("通过拦截器");
                return true;
            }
        }


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try{
            JsonResult json = new JsonResult(50002,"认证失败，未通过拦截器",null);
            response.getWriter().append(json.toString());
            System.out.println("认证失败，未通过拦截器");
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;

    }

}
