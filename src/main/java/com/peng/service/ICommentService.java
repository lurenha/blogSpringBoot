package com.peng.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Comment;

public interface ICommentService extends IService<Comment> {
    PageInfo<Comment> getListByPage(Integer pageNum, Integer pageSize);
//    Comment findByid(Integer co_id);
//    PageInfo<Comment> findpage(Integer pageNum,Integer pagesize);
//    boolean addORedit(Comment comment);
//    boolean deleteByid(Integer co_id);
}
