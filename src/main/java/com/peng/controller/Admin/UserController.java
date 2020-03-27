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
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private IUserService userService;

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
