package com.peng.controller.Admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.github.pagehelper.PageInfo;
import com.peng.aspect.MyCache;
import com.peng.config.exception.CaptchaExpireException;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.entity.User;
import com.peng.entity.sys.SysRole;
import com.peng.service.ISysRoleService;
import com.peng.service.IUserService;
import com.peng.util.Constants;
import com.peng.util.RedisUtil;
import com.peng.util.TokenUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ISysRoleService iSysRoleService;

    /**
     * 修改用户
     */
    //    @RequiresPermissions("user:addORedit")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody User user) {
        Boolean bool = iUserService.updateById(user);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 新增用户
     */
    //    @RequiresPermissions("user:addORedit")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody User user) {
        Boolean bool = iUserService.save(user);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 根据用户编号获取详细信息
     */
    //    @RequiresPermissions("user:find")
    @GetMapping("/{idNum}")
    public JsonResult getById(@PathVariable("idNum") Long usId) {
        Map<String, Object> map = new HashMap<>();
        User userInfo = iUserService.getById(usId);
        List<SysRole> roles = iSysRoleService.lambdaQuery().select(SysRole::getRoleId, SysRole::getRoleName, SysRole::getRoleSort, SysRole::getStatus).list();
        map.put("userInfo", userInfo);
        map.put("roles", roles);
        return ResultUtil.success(map, ResultCode.SUCCESS);
    }

    /**
     * 获取用户列表
     */
    //    @RequiresPermissions("user:find")
    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "userName", required = false) String userName) {
//        PageInfo<User> userPage = iUserService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<User>().like(Objects.nonNull(userName), User::getUsername, userName));
        PageInfo<User> userPage = iUserService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<User>().like(Objects.nonNull(userName), User::getUsername, userName));
        return ResultUtil.success(userPage, ResultCode.SUCCESS);
    }

    /**
     * 删除用户
     */
    //    @RequiresPermissions("user:find")
    @GetMapping("/delete/{userIds}")
    public JsonResult remove(@PathVariable Long[] userIds) {
        for (Long id :userIds) {
            if(iUserService.isAdmin(id)){
                return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
            }
        }
        boolean bool = iUserService.removeByIds(Arrays.asList(userIds));
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 重置密码
     */
    @PostMapping("/resetPwd")
    public JsonResult resetPwd(@RequestBody User user) {
        if (StringUtils.isBlank(user.getPassword()) || Objects.isNull(user.getUsId())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = iUserService.updateById(User.builder().password(user.getPassword()).usId(user.getUsId()).build());
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }


}
