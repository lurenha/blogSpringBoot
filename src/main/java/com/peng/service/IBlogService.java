package com.peng.service;

import com.github.pagehelper.PageInfo;
import com.peng.domain.Blog;

import java.util.List;
import java.util.Map;


public interface IBlogService {
    Blog findByid(Integer bl_id);
    Blog findByidPro(Integer bl_id);
    PageInfo<Blog> findpage(Integer pageNum,Integer pagesize,String title,Integer ty_id);
    PageInfo<Blog> findPubpage(Integer pageNum,Integer pagesize);
    boolean addORedit(Blog blog);
    boolean deleteByid(Integer bl_id);
    boolean setPublishedByid(Integer bl_id,Boolean stats);
    PageInfo<Blog> findsByTag(Integer pageNum, Integer pagesize,Integer ta_id);
    PageInfo<Blog> findsByType(Integer pageNum, Integer pagesize,Integer ty_id);
    Map findTimeLine();
    int addViews(Integer bl_id);
}
