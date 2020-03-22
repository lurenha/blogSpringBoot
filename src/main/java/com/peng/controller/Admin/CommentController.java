package com.peng.controller.Admin;


import com.github.pagehelper.PageInfo;
import com.peng.entity.Comment;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @RequestMapping(path = "/addORedit", method = RequestMethod.POST)
    public JsonResult saveOrUpdate(Comment comment) {
        boolean bool = commentService.saveOrUpdate(comment);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }


    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
    public JsonResult removeById(@PathVariable("idNum") Long coId) {
        boolean bool = commentService.removeById(coId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }


    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult getById(@PathVariable("idNum") Long coId) {
        Comment comment = commentService.getById(coId);
        return ResultUtil.success(comment, ResultCode.SUCCESS);
    }


    @RequestMapping("/list/{pageNum}")
    public JsonResult getListByPage(@PathVariable("pageNum") Integer pageNum, @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        PageInfo<Comment> listByPage = commentService.getListByPage(pageNum, pagesize);
        return ResultUtil.success(listByPage, ResultCode.SUCCESS);
    }
}
