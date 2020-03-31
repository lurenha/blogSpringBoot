package com.peng.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Comment;

public interface ICommentService extends IService<Comment> {
    PageInfo<Comment> getListByPage(Integer pageNum, Integer pageSize);
    boolean setDeleted(Long coId, boolean flag);

}
