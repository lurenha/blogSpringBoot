package com.peng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.dao.BlogDao;
import com.peng.domain.Blog;
import com.peng.domain.Comment;
import com.peng.domain.Tag;
import com.peng.domain.TimeLineBlog;
import com.peng.service.IBlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("IBlogService")
public class BlogService implements IBlogService {

    @Autowired
    private BlogDao blogDao;
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    private void backStack(List<Comment> parentlist, List<Comment> childlist) {
        if (childlist == null) {
            return;
        }
        for (Comment child : childlist) {
            backStack(parentlist, child.getChildcoments());
            Comment com = new Comment();
            BeanUtils.copyProperties(child, com);
            com.setChildcoments(null);
            parentlist.add(com);
        }

    }

    @Override
    public Blog findByidPro(Integer bl_id) {
        Blog blog = blogDao.findByidPro(bl_id);
        //递归遍历子节点 将节点深度>1的 所有子节点添加至父节点
        List<Comment> comments = blog.getComments();
        for (Comment comment : comments) {
            ArrayList<Comment> tem = new ArrayList<>();
            backStack(tem, comment.getChildcoments());
            comment.setChildcoments(tem);
        }
        return blog;
    }


    @Override
    public Blog findByid(Integer bl_id) {
        Blog blog = blogDao.findByid(bl_id);
        blog.setTag_ids(blog.getTags().stream().map(tag -> tag.getTa_id()).collect(Collectors.toList()));
        blog.setTags(null);
        return blog;
    }

    @Override
    public PageInfo<Blog> findpage(Integer pageNum, Integer pagesize, String title, Integer ty_id) {
        PageHelper.startPage(pageNum, pagesize);
        if (title != null && title.trim().length() > 0) {
            title = "%" + title + "%";
        } else {
            title = null;
        }
        List<Blog> bloglist = blogDao.findallBlog(title, ty_id);
        PageInfo<Blog> blogs = new PageInfo<>(bloglist);
        return blogs;

    }

    @Override
    public boolean addORedit(Blog blog) {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Integer bl_id = blog.getBl_id();
            blog.setFinaldate(sdf.format(new Date()));
            if (bl_id != null) {//更新
                blogDao.updateBlog(blog);
                blogDao.deleteBlog_tags(bl_id);
                if (blog.getTags() != null) {
                    blogDao.addBlog_tags(blog);
                }
            } else {//添加
                blog.setViews(0);
                blog.setCreatdate(sdf.format(new Date()));
                blogDao.addBlog(blog);
                if (blog.getTags() != null) {
                    blogDao.addBlog_tags(blog);
                }
            }
            dataSourceTransactionManager.commit(transactionStatus);//提交
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus);//回滚,防止程序异常而事务一直卡在哪里未提交
            return false;
        }

    }

    @Override
    public boolean deleteByid(Integer bl_id) {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            blogDao.deleteBlog_tags(bl_id);//先删除带外键的
            blogDao.deleteBlog_comments(bl_id);
            blogDao.deleteBlogbyid(bl_id);
            dataSourceTransactionManager.commit(transactionStatus);//提交
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus);//回滚,防止程序异常而事务一直卡在哪里未提交
            return false;
        }
    }

    @Override
    public boolean setPublishedByid(Integer bl_id, Boolean stats) {
        return blogDao.setPublishedByid(bl_id, stats) > 0;
    }

    @Override
    public PageInfo<Blog> findsByTag(Integer pageNum, Integer pagesize, Integer ta_id) {
        PageHelper.startPage(pageNum, pagesize);
        List<Blog> bloglist = blogDao.findsBlogbyTag(ta_id);
        PageInfo<Blog> blogs = new PageInfo<>(bloglist);
        return blogs;
    }

    @Override
    public PageInfo<Blog> findsByType(Integer pageNum, Integer pagesize, Integer ty_id) {
        PageHelper.startPage(pageNum, pagesize);
        List<Blog> bloglist = blogDao.findsBlogbyType(ty_id);
        PageInfo<Blog> blogs = new PageInfo<>(bloglist);
        return blogs;
    }

    @Override
    public Map findTimeLine() {
        Map<String, List<TimeLineBlog>> map = new TreeMap<>((o1,o2)->{return -1*(o1.compareTo(o2));});
        for (TimeLineBlog tlBlog : blogDao.findtimeLine()) {
            if(!map.containsKey(tlBlog.getMonth())){
                map.put(tlBlog.getMonth(), new ArrayList<>());
            }
            String temmouth = tlBlog.getMonth();
            tlBlog.setMonth(null);
            map.get(temmouth).add(tlBlog);
        }
        return map;
    }

    @Override
    public int addViews(Integer bl_id) {
        return blogDao.addViews(bl_id);
    }

}
