package com.peng.controller.Admin;


import com.peng.domain.JsonResult.JsonResult;
import com.peng.domain.User;
import com.peng.service.IBlogService;
import com.peng.service.IUserService;
import com.peng.util.TokenUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JsonResult getinfo(@RequestParam("token") String token) {
        Integer us_id = null;
        User user = null;
        if ((us_id = TokenUtil.getUserid(token)) != null) {
            user = userService.findByid(us_id);
            System.out.println(user);
            user.setPassword(null);
            user.setUsername(null);
            user.setUs_id(null);

            ArrayList<String> roles = new ArrayList<>();
            roles.add("admin");
            user.setRoles(roles);
            return new JsonResult(20000, "ok", user);
        }
        return new JsonResult(50008, "error", null);
    }

    @RequiresPermissions("user:addORedit")
    @RequestMapping(value = "/addORedit", method = RequestMethod.POST)
    public JsonResult  addORedit_user(User user) {
        user.setUs_id(1);
        user.setFinaldate(new Date());
        Boolean bool=userService.addORedit(user);
        if (bool) {
            return new JsonResult(20000, "提交成功!", bool);
        } else {
            return new JsonResult(50001, "提交失败!", bool);
        }
    }

    @RequiresPermissions("user:find")
    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult find_blog(@PathVariable("idNum") Integer us_id) {
        User user = userService.findByid(us_id);
        return new JsonResult(20000, "ok", user);
    }


}
