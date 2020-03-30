package com.peng.controller.Admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
import com.peng.storage.StorageService;
import com.peng.util.Constants;
import com.peng.util.FileUploadUtils;
import com.peng.util.RedisUtil;
import com.peng.util.TokenUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private FileUploadUtils fileUploadUtils;


    /**
     * 修改用户
     */
    //    @RequiresPermissions("user:addORedit")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody User user) {
        if (Objects.isNull(user.getUsId())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        if (iUserService.count(new LambdaQueryWrapper<User>().ne(User::getUsId, user.getUsId()).eq(User::getUsername, user.getUsername())) > 0) {
            return ResultUtil.faile(ResultCode.DATA_ALREADY_EXISTED_ROLE);
        }
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
        if (iUserService.count(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername())) > 0) {
            return ResultUtil.faile(ResultCode.DATA_ALREADY_EXISTED_ROLE);
        }

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
        PageInfo<User> userPage = iUserService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<User>().like(Objects.nonNull(userName), User::getUsername, userName));
        return ResultUtil.success(userPage, ResultCode.SUCCESS);
    }

    /**
     * 删除用户
     */
    //    @RequiresPermissions("user:find")
    @GetMapping("/delete/{userIds}")
    public JsonResult remove(@PathVariable Long[] userIds) {
        for (Long id : userIds) {
            if (iUserService.isAdmin(id)) {
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

    /**
     * 个人信息修改页
     */
    @GetMapping("/profile")
    public JsonResult profile(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Peng-Token");
        Long userId = TokenUtil.getUserId(token);
        User user = iUserService.getById(userId);
        user.setPassword(null);
        SysRole sysRole = iSysRoleService.getById(user.getRoleId());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roleName", sysRole.getRoleName());
        return ResultUtil.success(map, ResultCode.SUCCESS);
    }

    /**
     * 个人信息修改密码
     */
    @PostMapping("/updatePwd")
    public JsonResult profile(ServletRequest request, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Peng-Token");
        Long userId = TokenUtil.getUserId(token);
        User user = iUserService.getById(userId);
        if (!user.getPassword().equals(oldPassword)) {
            return ResultUtil.faile(ResultCode.PWD_AUTH_ERROR);
        }
        boolean bool = iUserService.update(new LambdaUpdateWrapper<User>().set(User::getPassword, newPassword).eq(User::getUsId, userId));
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 头像上传
     */
    @PostMapping("/profile/avatar")
    public JsonResult create(@RequestParam("avatarfile") MultipartFile file,ServletRequest request) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Peng-Token");
        Long userId = TokenUtil.getUserId(token);
        if (!file.isEmpty()) {
            String url = fileUploadUtils.upload(file);
            iUserService.update(new LambdaUpdateWrapper<User>().set(User::getAvatar, url).eq(User::getUsId, userId));
            return ResultUtil.success(url, ResultCode.SUCCESS);
        }
        return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
    }


}
