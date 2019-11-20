package com.peng.controller.Admin;

import com.github.pagehelper.PageInfo;
import com.peng.domain.Comment;
import com.peng.domain.JsonResult.JsonResult;
import com.peng.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @RequestMapping(path = "/addORedit", method = RequestMethod.POST)
    public JsonResult addORedit_comment(Comment comment) {
        boolean bool = commentService.addORedit(comment);
        if(bool){
            return new JsonResult(20000, "提交成功!", bool);
        }else {
            return new JsonResult(50001, "提交失败!", bool);
        }

    }


    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
    public JsonResult delete_comment(@PathVariable("idNum") Integer co_id) {
        boolean bool = commentService.deleteByid(co_id);
        if(bool){
            return new JsonResult(20000, "提交成功!", bool);
        }else {
            return new JsonResult(50001, "提交失败!", bool);
        }
    }


    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult find_comment(@PathVariable("idNum") Integer co_id) {
        Comment comment = commentService.findByid(co_id);
        return new JsonResult(20000, "ok", comment);
    }


    @RequestMapping("/list/{pageNum}")
    public JsonResult list_comment(@PathVariable("pageNum") Integer pageNum, @RequestParam(value="pagesize",defaultValue="10") Integer pagesize ) {
        PageInfo<Comment> findpage = commentService.findpage(pageNum,pagesize);
        return new JsonResult(20000, "ok", findpage);
    }
}
