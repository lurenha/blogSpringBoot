package com.peng.controller.Admin;


import com.peng.entity.Friend;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.service.IFriendService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/friend")
public class FriendController {
    @Autowired
    private IFriendService iFriendService;

    @RequiresPermissions("friend:addORedit")
    @RequestMapping(path = "/addORedit", method = RequestMethod.POST)
    public JsonResult saveOrUpdate(Friend friend) {
        boolean bool = iFriendService.saveOrUpdate(friend);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }


    @RequiresPermissions("friend:delete")
    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
    public JsonResult removeById(@PathVariable("idNum") Long frId) {
        boolean bool = iFriendService.removeById(frId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    @RequiresPermissions("friend:find")
    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult getById(@PathVariable("idNum") Long frId) {
        Friend friend = iFriendService.getById(frId);
        return ResultUtil.success(friend, ResultCode.SUCCESS);
    }

    @RequiresPermissions("friend:list")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public JsonResult list() {
        List<Friend> friendList = iFriendService.list();
        return ResultUtil.success(friendList, ResultCode.SUCCESS);
    }
}
