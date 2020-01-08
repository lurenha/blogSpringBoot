package com.peng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.dao.CommentDao;
import com.peng.domain.Comment;
import com.peng.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("ICommentService")
public class CommentService implements ICommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment findByid(Integer co_id) {
        return commentDao.findCommentByid(co_id);
    }


    //
    @Override
    public PageInfo<Comment> findpage(Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Comment> commentList = commentDao.findAllComment();
        PageInfo<Comment> comments = new PageInfo<>(commentList);
        return comments;
    }

    @Override
    public boolean addORedit(Comment comment) {
        Integer co_id = comment.getCo_id();
        if (co_id != null) {//更新
            commentDao.updateComment(comment);
        } else {//添加
            comment.setCreatdate(new Date());
            commentDao.addComment(comment);
        }
        return true;
    }

    @Override
    public boolean deleteByid(Integer co_id) {
        commentDao.deleteCommentByid(co_id);
        return true;
    }
}
