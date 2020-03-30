package com.peng.controller.Admin;


import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.entity.Tag;
import com.peng.service.ITagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/tag")
public class TagController {
    @Autowired
    private ITagService tagService;

//    @RequiresPermissions("tag:addORedit")
    @RequestMapping(path = "/addORedit", method = RequestMethod.POST)
    public JsonResult saveOrUpdate(Tag tag) {
        boolean bool = tagService.saveOrUpdate(tag);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }


//    @RequiresPermissions("tag:delete")
    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
    public JsonResult removeById(@PathVariable("idNum") Long taId) {
        boolean bool = tagService.removeById(taId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }


//    @RequiresPermissions("tag:find")
    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult getById(@PathVariable("idNum") Long taId) {
        Tag tag = tagService.getById(taId);
        return ResultUtil.success(tag, ResultCode.SUCCESS);
    }


//    @RequiresPermissions("tag:list")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public JsonResult list() {
        List<Tag> tagList = tagService.list();
        return ResultUtil.success(tagList, ResultCode.SUCCESS);
    }
}
