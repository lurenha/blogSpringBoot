package com.peng.controller.Admin;


import com.peng.aspect.MyLog;
import com.peng.entity.Comment;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.service.IBlogService;
import com.peng.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    @Autowired
    private IBlogService iBlogService;

    @PreAuthorize("hasAuthority('content:comment:query')")
    @GetMapping("/getCommentWithChildById/{idNum}")
    public JsonResult getCommentWithChildById(@PathVariable("idNum") Long blId) {
        List<Comment> commentList = iBlogService.getCommentWithChildById(blId);
        return ResultUtil.success(commentList, ResultCode.SUCCESS);

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:comment:delete')")
    @PostMapping("/setDeleted")
    public JsonResult setPublished(@RequestBody Comment comment) {
        if (Objects.isNull(comment.getCoId()) || Objects.isNull(comment.getIsDelete())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = iCommentService.setDeleted(comment.getCoId(), comment.getIsDelete());
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

}
