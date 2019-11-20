package com.peng.service;

import com.github.pagehelper.PageInfo;
import com.peng.domain.Comment;

public interface ICommentService {
    Comment findByid(Integer co_id);
    PageInfo<Comment> findpage(Integer pageNum,Integer pagesize);
    boolean addORedit(Comment comment);
    boolean deleteByid(Integer co_id);  
}
