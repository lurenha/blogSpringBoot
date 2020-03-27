package com.peng.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.peng.aspect.MyCache;
import com.peng.entity.*;
import com.peng.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CacheServiceImpl implements ICacheService {
    @Autowired
    private IBlogService iBlogService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICommentService iCommentService;
    @Autowired
    private ITypeService iTypeService;
    @Autowired
    private ITagService iTagService;
    @Autowired
    private IFriendService iFriendService;

    @Override
    @MyCache
    public PageInfo<Blog> getIndexPage(String title, Integer pageNum) {
        return iBlogService.getIndexPage(title, pageNum);
    }

    @Override
    @MyCache
    public List<Type> getIndexTypes() {
        return iTypeService.getIndexTypes();
    }

    @Override
    @MyCache
    public List<Tag> getIndexTags() {
        return iTagService.getIndexTags();
    }

    @Override
    @MyCache
    public Integer getPushedBlogNum() {
        return iBlogService.count(new LambdaQueryWrapper<Blog>().eq(Blog::getPublished, true));
    }

    @Override
    @MyCache
    public Integer getTypeNum() {
        return  iTypeService.count();
    }

    @Override
    @MyCache
    public Integer getTagNum() {
        return  iTagService.count();
    }

    @Override
    @MyCache
    public Integer getCommentNum() {
        return iCommentService.count();
    }

    @Override
    @MyCache
    public User getAdminInfo() {
        return iUserService.getAdminInfo();
    }

    @Override
    @MyCache
    public List<String> getPermissionList(Long usId) {
        return iUserService.getPermissionList(usId);
    }

    @Override
    @MyCache
    public PageInfo<Blog> getPageByType(Integer pageNum, Long tyId) {
        return iBlogService.getPageByType(pageNum,tyId);
    }

    @Override
    @MyCache
    public PageInfo<Blog> getPageByTag(Integer pageNum, Long taId) {
        return iBlogService.getPageByTag(pageNum, taId);
    }

    @Override
    @MyCache
    public Map findTimeLine() {
        return iBlogService.findTimeLine();
    }

    @Override
    @MyCache
    public List<Friend> getIndexFriends() {
        return iFriendService.list();
    }

}
