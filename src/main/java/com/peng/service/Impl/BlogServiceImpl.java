package com.peng.service.Impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Blog;
import com.peng.entity.Comment;
import com.peng.entity.other.TimeLineBlog;
import com.peng.mapper.BlogMapper;
import com.peng.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public PageInfo<Blog> getIndexPage(String title, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> list = blogMapper.findIndexPage(title);
        PageInfo<Blog> result = new PageInfo<>(list);
        return result;
    }

    @Override
    public PageInfo<Blog> getPageByType(Integer pageNum, Long tyId) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> list = blogMapper.getPageByType(tyId);
        PageInfo<Blog> result = new PageInfo<>(list);
        return result;
    }

    @Override
    public PageInfo<Blog> getPageByTag(Integer pageNum, Long taId) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> list = blogMapper.getPageByTag(taId);
        PageInfo<Blog> result = new PageInfo<>(list);
        return result;
    }


    @Override
    public PageInfo<Blog> getListByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> list = this.list();
        PageInfo<Blog> result = new PageInfo<>(list);
        return result;
    }

    @Override
    public PageInfo<Blog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<Blog> queryWrapper) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> list = this.list(queryWrapper);
        PageInfo<Blog> result = new PageInfo<>(list);
        return result;
    }

    @Override
    public boolean setPublished(Long blId, boolean flag) {
        return this.update(new LambdaUpdateWrapper<Blog>().eq(Blog::getBlId, blId).set(Blog::getPublished, flag));
    }

    @Override
    public Blog findFullById(Long blId) {
        Blog blog = blogMapper.findFullBlogById(blId);
        if (blog != null) {
            blog.setComments(getCommentWithChildById(blId));
        }
        return blog;
    }

    @Override
    public Blog findBlogWithTagIdsById(Long blId) {
        return blogMapper.findBlogWithTagIdsById(blId);
    }

    @Override
    public boolean addViews(Long blId) {
        blogMapper.addViewsById(blId);
        return true;
    }

    @Override
    public List<Comment> getCommentWithChildById(Long blId) {
        List<Comment> commentList = blogMapper.findCommentByBlog(blId);
        Map<Long, List<Comment>> commentMap = commentList.stream().filter(comment -> Objects.nonNull(comment.getParentId())).collect(Collectors.groupingBy(Comment::getParentId));
        List<Comment> resComments = commentList.stream().filter(comment -> Objects.isNull(comment.getParentId()))
                .peek(curComment -> {//将comment下所有子孙评论放入comment的ChildList
                    List<Comment> childList = new ArrayList<>();
                    getChildesByCur(childList, curComment, commentMap);
                    childList.sort(Comparator.comparing(Comment::getCreateTime).reversed());
                    curComment.setChildList(childList);
                }).sorted(Comparator.comparing(Comment::getCreateTime).reversed()).collect(Collectors.toList());
        return resComments;

    }

    /**
     * 递归 插入评论的父节点，子节点
     *
     * @param resList
     * @param curComment
     * @param commentMap
     */
    private void getChildesByCur(List<Comment> resList, Comment curComment, Map<Long, List<Comment>> commentMap) {
        if (curComment == null || curComment.getCoId() == null) {
            return;
        }
        Long key = curComment.getCoId();
        if (commentMap.containsKey(key)) {
            for (Comment nextComment : commentMap.get(key)) {
                //构造父节点，用于前端显示@Parent
                nextComment.setParent(Comment.builder().coId(curComment.getCoId()).name(curComment.getName()).build());
                resList.add(nextComment);
                getChildesByCur(resList, nextComment, commentMap);
            }
        }
    }


    @Override
    public Map findTimeLine() {
        Map<String, List<TimeLineBlog>> map = new LinkedHashMap<>();
        for (TimeLineBlog tlBlog : blogMapper.findTimeLine()) {
            if (!map.containsKey(tlBlog.getMonth())) {
                map.put(tlBlog.getMonth(), new ArrayList<>());
            }
            String temMonth = tlBlog.getMonth();
            tlBlog.setMonth(null);
            map.get(temMonth).add(tlBlog);
        }
        return map;
    }

    @Override
    @Transactional
    public Boolean updateBlogWithTag(Blog blog) {
        this.updateById(blog);
        blogMapper.deleteBlogTagBatch(blog.getBlId());
        if (Objects.nonNull(blog.getTagIds()) && blog.getTagIds().length > 0) {
            List<Long> tagIds = Arrays.asList(blog.getTagIds());
            blogMapper.addBlogTagBatch(blog.getBlId(), tagIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean addBlogWithTag(Blog blog) {
        this.save(blog);
        if (Objects.nonNull(blog.getTagIds()) && blog.getTagIds().length > 0) {
            List<Long> tagIds = Arrays.asList(blog.getTagIds());
            blogMapper.addBlogTagBatch(blog.getBlId(), tagIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteBlogWithTag(Long blId) {
        this.removeById(blId);
        blogMapper.deleteBlogTagBatch(blId);
        return true;
    }

}
