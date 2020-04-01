//package com.peng.springboot_one;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.github.pagehelper.PageInfo;
//import com.peng.entity.Blog;
//import com.peng.entity.Comment;
//import com.peng.mapper.*;
//import com.peng.mapper.sys.SysRoleMapper;
//import com.peng.service.IBlogService;
//import com.peng.service.ICommentService;
//import com.peng.service.ISysRoleService;
//import com.peng.service.IUserService;
//import com.peng.util.RedisUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MyTests {
//    @Autowired
//    private RedisUtil redisUtil;
//    @Autowired
//    private BlogMapper blogMapper;
//    @Autowired
//    private CommentMapper commentMapper;
//    @Autowired
//    private FriendMapper friendMapper;
//    @Autowired
//    private RequestLogMapper requestLogMapper;
//    @Autowired
//    private TagMapper tagMapper;
//    @Autowired
//    private TypeMapper typeMapper;
//    @Autowired
//    private UserMapper userMapper;
//
//
//    @Autowired
//    private ICommentService iCommentService;
//
//    @Autowired
//    private IBlogService iblogService;
//
//    @Autowired
//    private IUserService iUserService;
//
//    @Autowired
//    private ISysRoleService iSysRoleService;
//
//    @Autowired
//    private SysRoleMapper sysRoleMapper;
//
//
//    @Test
//    public void test01() {
//        Integer pageNum = 2;
//        Integer pageSize = 5;
//        Long tyId = null;
//        String title = null;
//        PageInfo<Blog> listByPage = iblogService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<Blog>().eq(Objects.nonNull(tyId), Blog::getTyId, tyId).like(Objects.nonNull(title), Blog::getTitle, title));
//        for (Blog blog : listByPage.getList()
//        ) {
//            System.out.println(blog.getTitle());
//
//        }
//    }
//
//    @Test
//    public void test02() {
//        List<Comment> commentList = iblogService.getCommentWithChildById(1l);
//        for (Comment comment:commentList
//             ) {
//            System.out.println(comment);
//
//        }
//
//    }
//
//
//    @Test
//    public void test03() {
//        System.out.println("生成验证码，并存入redis");
//        Map<String, Object> map = new HashMap();
//        map.put("id", 1);
//        map.put("code", 123321);
//        map.put("count", 0);
//        redisUtil.hmset("phone+Terminal", map, 3000);
//    }
//
//    @Test
//    public void test04() {
//        System.out.println("从redis中验证");
//        if (redisUtil.hHasKey("phone+Terminal", "count")) {//
//            int count = (int) redisUtil.hget("phone+Terminal", "count");
//            if (count < 5) {
//                //去验证
//                //。。。。。
//                //验证之后 count+1
//                redisUtil.hincr("phone+Terminal", "count", 1);
//                System.out.println("count+1了,现在count为:" + redisUtil.hget("phone+Terminal", "count"));
//            } else {
//                System.out.println("验证超过5次了");
//            }
//        } else {
//            System.out.println("redis中没有对应的信息");
//        }
//    }
//
//}
