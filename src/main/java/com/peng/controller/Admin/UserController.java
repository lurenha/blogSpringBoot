package com.peng.controller.Admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.entity.User;
import com.peng.service.IUserService;
import com.peng.util.TokenUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestParam("account") String account, @RequestParam("password") String password) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.verifyLogin(account, password);
        if (user != null) {
            String token = TokenUtil.sign(user);
            map.put("token", token);
            return ResultUtil.success(map, ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.USER_LOGIN_ERROR);
        }

    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JsonResult getInfo(@RequestParam("token") String token) {
        Long usId = null;
        User user = null;
        if ((usId = TokenUtil.getUserId(token)) != null) {
            user = userService.getById(usId);
            user.setPassword(null);
            user.setAccount(null);
            user.setUsId(null);
            ArrayList<String> roles = new ArrayList<>();
            roles.add("admin");
            user.setRoles(roles);
            return ResultUtil.success(user, ResultCode.SUCCESS);
        }
        return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
    }

    @RequiresPermissions("user:addORedit")
    @RequestMapping(value = "/addORedit", method = RequestMethod.POST)
    public JsonResult saveOrUpdate(User user) {
        Boolean bool = userService.saveOrUpdate(user);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    @RequiresPermissions("user:find")
    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult getById(@PathVariable("idNum") Long usId) {
        User user = userService.getById(usId);
        return ResultUtil.success(user, ResultCode.SUCCESS);
    }


}
