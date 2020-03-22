package com.peng.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Blog;

import java.util.Map;


public interface IBlogService extends IService<Blog> {
    PageInfo<Blog> getListByPage(Integer pageNum,Integer pageSize);
    PageInfo<Blog> getListByPage(Integer pageNum,Integer pageSize, Wrapper<Blog> queryWrapper);
    boolean setPublished(Long blId,boolean flag);
//    Blog findByid(Integer bl_id);
//    Blog findByidPro(Integer bl_id);
//    PageInfo<Blog> findpage(Integer pageNum,Integer pagesize,String title,Integer ty_id);
//    PageInfo<Blog> findPubpage(Integer pageNum,Integer pagesize,String title);
//    boolean addORedit(Blog blog);
//    boolean deleteByid(Integer bl_id);
//    boolean setPublishedByid(Integer bl_id,Boolean stats);
//    PageInfo<Blog> findsByTag(Integer pageNum, Integer pagesize,Integer ta_id);
//    PageInfo<Blog> findsByType(Integer pageNum, Integer pagesize,Integer ty_id);
//    Map findTimeLine();
//    int addViews(Integer bl_id);
//    int getPusBlogs();
}
