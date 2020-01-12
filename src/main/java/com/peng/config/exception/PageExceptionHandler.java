package com.peng.config.exception;

import com.peng.domain.JsonResult.JsonResult;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.*;


/***
 * 全局异常处理
 */
@ControllerAdvice
public class PageExceptionHandler {


    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public Object handleException(UnauthorizedException e) {
        // 记录错误信息
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", msg);
        return new JsonResult(50000,"该角色权限不足!",jsonObject);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        // 记录错误信息
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", msg);
        return new JsonResult(50000,msg,jsonObject);
    }

    //
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView pageExceptionHandle(HttpServletRequest req, Exception e) throws Exception {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("msg", e.getMessage());
//        modelAndView.addObject("url", req.getRequestURL());
//        // 模板名称
//        modelAndView.setViewName("error");
//        return modelAndView;
//
//    }
//
//    @RestController
//    public class FinalExceptionHandler implements ErrorController {
//        @Override
//        public String getErrorPath() {
//            return "/error";
//        }
//
//        @RequestMapping(value = "/error")
//        public Object error(HttpServletResponse resp, HttpServletRequest req) {
//            // 错误处理逻辑
//            return "其他异常";
//        }
//    }

}
