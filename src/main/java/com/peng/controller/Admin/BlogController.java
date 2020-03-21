package com.peng.controller.Admin;


import com.peng.entity.Blog;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
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
    public JsonResult removeById(@PathVariable("idNum") Integer blId) {
        boolean bool = blogService.removeById(blId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    @RequiresPermissions("blog:find")
    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult getById(@PathVariable("idNum") Integer blId) {
        Blog blog = blogService.getById(blId);
        return ResultUtil.success(blog,ResultCode.SUCCESS);
    }

//    @RequiresPermissions(logical = Logical.AND, value = {"blog:list"})
//    @RequestMapping("/list")
//    public JsonResult list_blog(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
//                                @RequestParam(value = "limit", defaultValue = "10") Integer pagesize,
//                                @RequestParam(value = "title", required = false) String title,
//                                @RequestParam(value = "type", required = false) Integer ty_id
//    ) {
//        blogService.
//        PageInfo<Blog> findpage = blogService.findpage(pageNum, pagesize, title, ty_id);
//        return new JsonResult(20000, "ok", findpage);
//    }
//
//
//    @RequiresPermissions("blog:addORedit")
//    @RequestMapping(path = "/setPublished", method = RequestMethod.POST)
//    public JsonResult setPublished(@RequestParam(value = "bl_id") Integer bl_id, @RequestParam(value = "published") Boolean published) {
//        boolean bool = blogService.setPublishedByid(bl_id, published);
//        if (bool) {
//            return new JsonResult(20000, "提交成功!", bool);
//        } else {
//            return new JsonResult(50001, "提交失败!", bool);
//        }
//    }

}
