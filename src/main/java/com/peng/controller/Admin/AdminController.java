package com.peng.controller.Admin;


import com.peng.domain.JsonResult.JsonResult;
import com.peng.domain.User;
import com.peng.service.IBlogService;
import com.peng.service.IUserService;
import com.peng.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public JsonResult verilogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.verifylogin(username, password);
        if (user != null) {
            String token = TokenUtil.sign(user);
            map.put("token", token);
            return new JsonResult(20000, "认证成功", map);
        } else {
            return new JsonResult(50000, "认证失败", null);
        }

    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public JsonResult getinfo(@RequestParam("token") String token) {
        Integer us_id = null;
        User user = null;
        if ((us_id = TokenUtil.getUserid(token)) != null) {
            user = userService.findByid(us_id);
            user.setPassword(null);
            user.setUsername(null);
            user.setUs_id(null);
            user.setRoles(new String[]{"admin"});
            return new JsonResult(20000, "ok", user);
        }
        return new JsonResult(50008, "error", null);
    }


}
