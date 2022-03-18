package com.peng.controller.Admin;


import com.peng.aspect.MyLog;
import com.peng.entity.Friend;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/friend")
public class FriendController {
    @Autowired
    private IFriendService iFriendService;

    @MyLog
    @PreAuthorize("hasAuthority('content:friend:add')")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody Friend friend) {
        boolean bool = iFriendService.save(friend);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:friend:edit')")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody Friend friend) {
        boolean bool = iFriendService.updateById(friend);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:friend:remove')")
    @GetMapping("/delete/{idNum}")
    public JsonResult removeById(@PathVariable("idNum") Long frId) {
        boolean bool = iFriendService.removeById(frId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    @PreAuthorize("hasAuthority('content:friend:query')")
    @GetMapping("/find/{idNum}")
    public JsonResult getById(@PathVariable("idNum") Long frId) {
        Friend friend = iFriendService.getById(frId);
        return ResultUtil.success(friend, ResultCode.SUCCESS);
    }

    @PreAuthorize("hasAuthority('content:friend:query')")
    @GetMapping("/list")
    public JsonResult list() {
        List<Friend> friendList = iFriendService.list();
        return ResultUtil.success(friendList, ResultCode.SUCCESS);
    }
}
