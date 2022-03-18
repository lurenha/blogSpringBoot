package com.peng.controller.Admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.peng.aspect.MyLog;
import com.peng.entity.Blog;
import com.peng.entity.Comment;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.service.IBlogService;
import com.peng.service.ICommentService;
import com.peng.util.FileUploadUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/admin/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICommentService iCommentService;
    @Autowired
    private FileUploadUtils fileUploadUtils;

    @MyLog
    @PreAuthorize("hasAuthority('content:blog:add')")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody Blog blog) {
        boolean bool = blogService.addBlogWithTag(blog);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:blog:edit')")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody Blog blog) {
        if (Objects.isNull(blog.getBlId())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = blogService.updateBlogWithTag(blog);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:blog:remove')")
    @GetMapping("/delete/{idNum}")
    public JsonResult removeById(@PathVariable("idNum") Long blId) {

        boolean bool = blogService.deleteBlogWithTag(blId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    @PreAuthorize("hasAuthority('content:blog:query')")
    @GetMapping("/find/{idNum}")
    public JsonResult getById(@PathVariable("idNum") Long blId) {
        Blog blog = blogService.findBlogWithTagIdsById(blId);
        return ResultUtil.success(blog, ResultCode.SUCCESS);
    }

    @PreAuthorize("hasAuthority('content:blog:query')")
    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "limit", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "type", required = false) Long tyId
    ) {
        PageInfo<Blog> listByPage = blogService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<Blog>().eq(Objects.nonNull(tyId), Blog::getTyId, tyId).like(Strings.isNotBlank(title), Blog::getTitle, title).select(Blog::getTitle, Blog::getTyId, Blog::getPublished, Blog::getViews, Blog::getBlId, Blog::getBackgroundImage, Blog::getCreateTime, Blog::getOutline, Blog::getUpdateTime).orderByDesc(Blog::getCreateTime));
        return ResultUtil.success(listByPage, ResultCode.SUCCESS);
    }

    @MyLog
    @PreAuthorize("hasAuthority('content:blog:setPub')")
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

    @PostMapping("/upload")
    public JsonResult uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String url = fileUploadUtils.upload(file);
            return ResultUtil.success(url, ResultCode.SUCCESS);
        }
        return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/hasCommentDic")
    public JsonResult getBlogHasCommentDictionaries() {
        List<Object> blIds = iCommentService.listObjs(new LambdaQueryWrapper<Comment>().select(Comment::getBlId)).stream().distinct().collect(Collectors.toList());
        List<Blog> blogListDic = blogService.list(new LambdaQueryWrapper<Blog>().in(Blog::getBlId, blIds).select(Blog::getBlId, Blog::getTitle));
        return ResultUtil.success(blogListDic, ResultCode.SUCCESS);
    }

}
