package com.peng.controller.Admin;

import com.peng.aspect.MyCache;
import com.peng.config.exception.CaptchaExpireException;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.entity.User;
import com.peng.service.IUserService;
import com.peng.util.Constants;
import com.peng.util.RedisUtil;
import com.peng.util.TokenUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("code") String code, @RequestParam("uuid") String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = (String) redisUtil.get(verifyKey);
        redisUtil.del(verifyKey);
        if (captcha == null) {
            throw new CaptchaExpireException("验证码不存在");
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaExpireException("验证码不匹配");
        }
        Map<String, Object> map = new HashMap<>();
        User user = userService.verifyLogin(username, password);
        if (user != null) {
            String token = TokenUtil.sign(user);
            map.put("token", token);
            return ResultUtil.success(map, ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.USER_LOGIN_ERROR);
        }

    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public JsonResult getInfo(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Peng-Token");

        Long usId = null;
        User user = null;
        if ((usId = TokenUtil.getUserId(token)) != null) {
            user = userService.getById(usId);
            user.setPassword(null);
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
