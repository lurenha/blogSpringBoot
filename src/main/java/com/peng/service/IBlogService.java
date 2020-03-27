package com.peng.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Blog;

import java.util.Map;


public interface IBlogService extends IService<Blog> {
    PageInfo<Blog> getIndexPage(String title, Integer pageNum);

    PageInfo<Blog> getPageByType(Integer pageNum,Long tyId);

    PageInfo<Blog> getPageByTag(Integer pageNum,Long taId);

    PageInfo<Blog> getListByPage(Integer pageNum, Integer pageSize);

    PageInfo<Blog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<Blog> queryWrapper);

    boolean setPublished(Long blId, boolean flag);

    Blog findFullById(Long blId);

    void addViews(Blog blog);

    Map findTimeLine();

}
