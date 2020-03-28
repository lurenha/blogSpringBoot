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
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @RequiresPermissions("user:addORedit")
    @RequestMapping(value = "/addORedit", method = RequestMethod.POST)
    public JsonResult saveOrUpdate(User user) {
        Boolean bool = iUserService.saveOrUpdate(user);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

//    @RequiresPermissions("user:find")
    @GetMapping("/{idNum}")
    public JsonResult getById(@PathVariable("idNum") Long usId) {
        User user = iUserService.getById(usId);
        return ResultUtil.success(user, ResultCode.SUCCESS);
    }

//    @RequiresPermissions("user:find")
    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResultUtil.success(iUserService.getListByPage(pageNum, pageSize), ResultCode.SUCCESS);
    }

}
