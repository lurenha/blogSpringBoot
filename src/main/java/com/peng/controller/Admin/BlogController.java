package com.peng.controller.Admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Blog;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.service.IBlogService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/admin/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    //    @RequiresPermissions("blog:addORedit")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody Blog blog) {
        boolean bool = blogService.save(blog);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    //    @RequiresPermissions("blog:addORedit")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody Blog blog) {
        boolean bool = blogService.updateById(blog);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }


    //    @RequiresPermissions("blog:delete")
    @GetMapping("/delete/{idNum}")
    public JsonResult removeById(@PathVariable("idNum") Long blId) {
        boolean bool = blogService.removeById(blId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    //    @RequiresPermissions("blog:find")
    @GetMapping("/find/{idNum}")
    public JsonResult getById(@PathVariable("idNum") Long blId) {
        Blog blog = blogService.getById(blId);
        return ResultUtil.success(blog, ResultCode.SUCCESS);
    }

    //    @RequiresPermissions(logical = Logical.AND, value = {"blog:list"})
    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "limit", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "type", required = false) Long tyId
    ) {
        PageInfo<Blog> listByPage = blogService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<Blog>().eq(Objects.nonNull(tyId), Blog::getTyId, tyId).like(Objects.nonNull(title), Blog::getTitle, title).select(Blog::getTitle, Blog::getTyId, Blog::getPublished, Blog::getViews, Blog::getBlId, Blog::getBackgroundImage, Blog::getCreateTime, Blog::getOutline, Blog::getUpdateTime));
        return ResultUtil.success(listByPage, ResultCode.SUCCESS);
    }


    //    @RequiresPermissions("blog:addORedit")
    @PostMapping("/setPublished")
    public JsonResult setPublished(@RequestBody Blog blog) {
        if (Objects.isNull(blog.getBlId()) || Objects.isNull(blog.getPublished())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = blogService.setPublished(blog.getBlId(), blog.getPublished());
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

}
