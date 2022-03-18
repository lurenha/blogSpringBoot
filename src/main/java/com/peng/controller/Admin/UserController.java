package com.peng.controller.Admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.peng.entity.MyUser;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.entity.User;
import com.peng.entity.sys.SysRole;
import com.peng.service.ISysRoleService;
import com.peng.service.IUserService;
import com.peng.util.FileUploadUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PreAuthorize("hasAuthority('system:user:edit')")
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
    @PreAuthorize("hasAuthority('system:user:add')")
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
    @PreAuthorize("hasAuthority('system:user:query')")
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
    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "userName", required = false) String userName) {
        PageInfo<User> userPage = iUserService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<User>().like(Strings.isNotBlank(userName), User::getUsername, userName));
        return ResultUtil.success(userPage, ResultCode.SUCCESS);
    }

    /**
     * 删除用户
     */
    @PreAuthorize("hasAuthority('system:user:remove')")
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
    @PreAuthorize("hasAuthority('system:user:resetPwd')")
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
     * 个人信息页
     */
    @GetMapping("/profile")
    public JsonResult profile(MyUser myUser) {
        myUser.setPassword(null);
        Map<String, Object> map = new HashMap<>();
        map.put("user", myUser);
        map.put("roleName", myUser.getRole().getRoleName());
        return ResultUtil.success(map, ResultCode.SUCCESS);
    }

    /**
     * 个人信息页修改
     */
    @PostMapping("/profile/update")
    public JsonResult profileUpdate(@Validated @RequestBody User user, MyUser myUser) {
        if (myUser == null) {
            return ResultUtil.faile(ResultCode.Token_AUTH_ERROR);
        }
        if (iUserService.count(new LambdaQueryWrapper<User>().ne(User::getUsId, myUser.getUsId()).eq(User::getUsername, user.getUsername())) > 0) {
            return ResultUtil.faile(ResultCode.DATA_ALREADY_EXISTED_ROLE);
        }
        user.setUsId(myUser.getUsId());
        user.setPassword(null);
        user.setAvatar(null);
        Boolean bool = iUserService.updateById(user);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 个人信息修改密码
     */
    @PostMapping("/profile/updatePwd")
    public JsonResult profile(MyUser myUser, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword) {
        if (myUser == null) {
            return ResultUtil.faile(ResultCode.Token_AUTH_ERROR);
        }
        if (!myUser.getPassword().equals(oldPassword)) {
            return ResultUtil.faile(ResultCode.PWD_AUTH_ERROR);
        }
        boolean bool = iUserService.update(new LambdaUpdateWrapper<User>().set(User::getPassword, newPassword).eq(User::getUsId, myUser.getUsId()));
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
    public JsonResult create(@RequestParam("avatarfile") MultipartFile file, MyUser myUser) throws IOException {
        if (myUser == null) {
            return ResultUtil.faile(ResultCode.Token_AUTH_ERROR);
        }
        if (!file.isEmpty()) {
            String url = fileUploadUtils.upload(file);
            iUserService.update(new LambdaUpdateWrapper<User>().set(User::getAvatar, url).eq(User::getUsId, myUser.getUsId()));
            return ResultUtil.success(url, ResultCode.SUCCESS);
        }
        return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
    }


}
