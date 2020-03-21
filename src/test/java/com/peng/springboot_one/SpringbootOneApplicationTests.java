package com.peng.springboot_one;

import com.peng.entity.Blog;
import com.peng.entity.User;
import com.peng.mapper.*;
import com.peng.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test01() {
        System.out.println(tagMapper.selectById(2));
        System.out.println(typeMapper.selectById(2));
        System.out.println(userMapper.selectById(1));
    }

}
