package com.peng.springboot_one;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.entity.Blog;
import com.peng.entity.Comment;
import com.peng.entity.User;
import com.peng.mapper.*;
import com.peng.service.IBlogService;
import com.peng.service.ICommentService;
import com.peng.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootOneApplicationTests {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private IPAddressMapper ipAddressMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private UserMapper userMapper;


    @Autowired
    private ICommentService iCommentService;

    @Autowired
    private IBlogService blogService;


    @Test
    public void test01() {
        Integer pageNum=2;
        Integer pageSize=5;
        Long tyId=null;
        String title=null;
        PageInfo<Blog> listByPage = blogService.getListByPage(pageNum, pageSize,new LambdaQueryWrapper<Blog>().eq(Objects.nonNull(tyId),Blog::getTyId,tyId).like(Objects.nonNull(title),Blog::getTitle,title));
        for (Blog blog:listByPage.getList()
             ) {
            System.out.println(blog.getTitle());

        }
    }

    @Test
    public void test02() {
        System.out.println(  blogService.setPublished(1l, true));

    }


}
