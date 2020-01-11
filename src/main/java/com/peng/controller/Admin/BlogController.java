package com.peng.controller.Admin;

import com.github.pagehelper.PageInfo;
import com.peng.domain.Blog;
import com.peng.domain.JsonResult.JsonResult;
import com.peng.service.IBlogService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @RequiresPermissions("blog:addORedit")
    @RequestMapping(path = "/addORedit", method = RequestMethod.POST)
    public JsonResult addORedit_blog(Blog blog) {
        boolean bool = blogService.addORedit(blog);
        if (bool) {
            return new JsonResult(20000, "提交成功!", bool);
        } else {
            return new JsonResult(50001, "提交失败!", bool);
        }

    }

    @RequiresPermissions("blog:delete")
    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
    public JsonResult delete_blog(@PathVariable("idNum") Integer bl_id) {
        boolean bool = blogService.deleteByid(bl_id);
        if (bool) {
            return new JsonResult(20000, "提交成功!", bool);
        } else {
            return new JsonResult(50001, "提交失败!", bool);
        }
    }

    @RequiresPermissions("blog:find")
    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult find_blog(@PathVariable("idNum") Integer bl_id) {
        Blog blog = blogService.findByid(bl_id);
        return new JsonResult(20000, "ok", blog);
    }

    @RequiresPermissions(logical = Logical.AND, value = {"blog:list"})
    @RequestMapping("/list")
    public JsonResult list_blog(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "limit", defaultValue = "10") Integer pagesize,
                                @RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "type", required = false) Integer ty_id
    ) {
        PageInfo<Blog> findpage = blogService.findpage(pageNum, pagesize, title, ty_id);
        return new JsonResult(20000, "ok", findpage);
    }


    @RequiresPermissions("blog:addORedit")
    @RequestMapping(path = "/setPublished", method = RequestMethod.POST)
    public JsonResult setPublished(@RequestParam(value = "bl_id") Integer bl_id, @RequestParam(value = "published") Boolean published) {
        boolean bool = blogService.setPublishedByid(bl_id, published);
        if (bool) {
            return new JsonResult(20000, "提交成功!", bool);
        } else {
            return new JsonResult(50001, "提交失败!", bool);
        }
    }

}
