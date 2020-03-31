package com.peng.controller.Admin;


import com.peng.entity.Friend;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.service.IFriendService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/friend")
public class FriendController {
    @Autowired
    private IFriendService iFriendService;

    //    @RequiresPermissions("friend:addORedit")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody Friend friend) {
        boolean bool = iFriendService.save(friend);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    //    @RequiresPermissions("friend:addORedit")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody Friend friend) {
        boolean bool = iFriendService.updateById(friend);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }


    //    @RequiresPermissions("friend:delete")
    @GetMapping("/delete/{idNum}")
    public JsonResult removeById(@PathVariable("idNum") Long frId) {
        boolean bool = iFriendService.removeById(frId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    //    @RequiresPermissions("friend:find")
    @GetMapping("/find/{idNum}")
    public JsonResult getById(@PathVariable("idNum") Long frId) {
        Friend friend = iFriendService.getById(frId);
        return ResultUtil.success(friend, ResultCode.SUCCESS);
    }

    //    @RequiresPermissions("friend:list")
    @GetMapping("/list")
    public JsonResult list() {
        List<Friend> friendList = iFriendService.list();
        return ResultUtil.success(friendList, ResultCode.SUCCESS);
    }
}
