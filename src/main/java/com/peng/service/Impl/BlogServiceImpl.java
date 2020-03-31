package com.peng.service.Impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.aspect.MyCache;
import com.peng.entity.Blog;
import com.peng.entity.Comment;
import com.peng.entity.other.TimeLineBlog;
import com.peng.mapper.BlogMapper;
import com.peng.service.IBlogService;
import com.peng.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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
        //递归遍历子节点 将节点深度>1的 所有子节点添加至父节点
        List<Comment> comments = blog.getComments();
        for (Comment comment : comments) {
            ArrayList<Comment> tem = new ArrayList<>();
            backStack(tem, comment.getChildList());
            tem.sort(((o1, o2) -> {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }));
            comment.setChildList(tem);
        }
        return blog;
    }

    @Override
    public Blog findBlogWithTagIdsById(Long blId) {
        return blogMapper.findBlogWithTagIdsById(blId);
    }

    @Override
    public void addViews(Blog blog) {
        this.update(new LambdaUpdateWrapper<Blog>().set(Blog::getViews, blog.getViews() + 1));
    }

    private void backStack(List<Comment> parentlist, List<Comment> childlist) {
        if (childlist == null) {
            return;
        }
        for (Comment child : childlist) {
            backStack(parentlist, child.getChildList());
            Comment com = new Comment();
            BeanUtils.copyProperties(child, com);
            com.setChildList(null);
            parentlist.add(com);
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
