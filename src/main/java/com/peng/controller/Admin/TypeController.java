package com.peng.controller.Admin;



import com.peng.domain.JsonResult.JsonResult;
import com.peng.domain.Type;
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
    public JsonResult addORedit_type(Type type) {
        boolean bool = typeService.addORedit(type);
        if (bool) {
            return new JsonResult(20000, "提交成功!", bool);
        } else {
            return new JsonResult(50001, "提交失败!", bool);
        }

    }


    @RequiresPermissions("type:delete")
    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
    public JsonResult delete_type(@PathVariable("idNum") Integer ty_id) {
        boolean bool = typeService.deleteByid(ty_id);
        if (bool) {
            return new JsonResult(20000, "提交成功!", bool);
        } else {
            return new JsonResult(50001, "提交失败!", bool);
        }
    }


    @RequiresPermissions("type:find")
    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult find_type(@PathVariable("idNum") Integer ty_id) {
        Type type = typeService.findByid(ty_id);
        return new JsonResult(20000, "ok", type);
    }


    @RequiresPermissions("type:list")
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public JsonResult list_type() {
        List<Type> allType = typeService.findall();
        return new JsonResult(20000, "ok", allType);
    }
}
