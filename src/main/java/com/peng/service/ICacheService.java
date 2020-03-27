package com.peng.service;

import com.github.pagehelper.PageInfo;
import com.peng.entity.*;

import java.util.List;
import java.util.Map;

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
    PageInfo<Blog> getPageByType(Integer pageNum,Long tyId);
    PageInfo<Blog> getPageByTag(Integer pageNum,Long taId);
    Map findTimeLine();
    List<Friend> getIndexFriends();
}
