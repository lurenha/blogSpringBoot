package com.peng.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Comment;
import com.peng.mapper.CommentMapper;
import com.peng.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Override
    public PageInfo<Comment> getListByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> list = this.list();
        PageInfo<Comment> result = new PageInfo<>(list);
        return result;
    }
//    @Autowired
//    private CommentDao commentDao;
//
//    @Override
//    public Comment findByid(Integer co_id) {
//        return commentDao.findCommentById(co_id);
//    }
//
//
//    //
//    @Override
//    public PageInfo<Comment> findpage(Integer pageNum, Integer pagesize) {
//        PageHelper.startPage(pageNum, pagesize);
//        List<Comment> commentList = commentDao.findAllComment();
//        PageInfo<Comment> comments = new PageInfo<>(commentList);
//        return comments;
//    }
//
//    @Override
//    public boolean addORedit(Comment comment) {
//        Integer co_id = comment.getCo_id();
//        if (co_id != null) {//更新
//            commentDao.updateComment(comment);
//        } else {//添加
//            comment.setCreatdate(new Date());
//            commentDao.addComment(comment);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean deleteByid(Integer co_id) {
//        commentDao.deleteCommentById(co_id);
//        return true;
//    }
}