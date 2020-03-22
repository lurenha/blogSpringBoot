package com.peng.controller.Admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Blog;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.service.IBlogService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/admin/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @RequiresPermissions("blog:addORedit")
    @RequestMapping(path = "/addORedit", method = RequestMethod.POST)
    public JsonResult saveOrUpdate(Blog blog) {
        boolean bool = blogService.saveOrUpdate(blog);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    @RequiresPermissions("blog:delete")
    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
    public JsonResult removeById(@PathVariable("idNum") Long blId) {
        boolean bool = blogService.removeById(blId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    @RequiresPermissions("blog:find")
    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult getById(@PathVariable("idNum") Long blId) {
        Blog blog = blogService.getById(blId);
        return ResultUtil.success(blog, ResultCode.SUCCESS);
    }

    @RequiresPermissions(logical = Logical.AND, value = {"blog:list"})
    @RequestMapping("/list")
    public JsonResult list(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "limit", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "type", required = false) Long tyId
    ) {
        PageInfo<Blog> listByPage = blogService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<Blog>().eq(Objects.nonNull(tyId), Blog::getTyId, tyId).like(Objects.nonNull(title), Blog::getTitle, title));
        return ResultUtil.success(listByPage, ResultCode.SUCCESS);
    }


    @RequiresPermissions("blog:addORedit")
    @RequestMapping(path = "/setPublished", method = RequestMethod.POST)
    public JsonResult setPublished(@RequestParam(value = "blId") Long blId, @RequestParam(value = "published") Boolean published) {
        boolean bool = blogService.setPublished(blId, published);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

}
