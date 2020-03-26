package com.peng.service;

import com.github.pagehelper.PageInfo;
import com.peng.entity.Blog;
import com.peng.entity.Tag;
import com.peng.entity.Type;
import com.peng.entity.User;

import java.util.List;

public interface ICacheService {
    PageInfo<Blog> getIndexPage(String title, Integer pageNum);
    List<Type> getIndexTypes();
    List<Tag> getIndexTags();
    Integer getPushedBlogNum();
    Integer getTypeNum();
    Integer getTagNum();
    Integer getCommentNum();
    User getAdminInfo();
    List<String> getPermissionList(Long usId);
}
