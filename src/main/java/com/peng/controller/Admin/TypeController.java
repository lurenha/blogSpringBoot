package com.peng.controller.Admin;


import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.entity.Type;
import com.peng.service.ITypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/type")
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @RequiresPermissions("type:addORedit")
    @RequestMapping(path = "/addORedit", method = RequestMethod.POST)
    public JsonResult saveOrUpdate(Type type) {
        boolean bool = typeService.saveOrUpdate(type);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }


    @RequiresPermissions("type:delete")
    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
    public JsonResult removeById(@PathVariable("idNum") Long tyId) {
        boolean bool = typeService.removeById(tyId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }


    @RequiresPermissions("type:find")
    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult getById(@PathVariable("idNum") Long tyId) {
        Type type = typeService.getById(tyId);
        return ResultUtil.success(type, ResultCode.SUCCESS);
    }


    @RequiresPermissions("type:list")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public JsonResult list() {
        List<Type> typeList = typeService.list();
        return ResultUtil.success(typeList, ResultCode.SUCCESS);
    }
}
