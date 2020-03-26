package com.peng.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peng.aspect.MyCache;
import com.peng.entity.Blog;
import com.peng.entity.User;
import com.peng.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

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
    private ICacheService iCacheService;


    @GetMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer pageNum, @RequestParam(required = false, value = "title") String title, Model model) {
        model.addAttribute("page", iCacheService.getIndexPage(title, pageNum));
        model.addAttribute("types", iCacheService.getIndexTypes());
        model.addAttribute("tags", iCacheService.getIndexTags());
        model.addAttribute("blogsCount", iCacheService.getPushedBlogNum());
        model.addAttribute("typesCount", iCacheService.getTypeNum());
        model.addAttribute("tagsCount", iCacheService.getTagNum());
        model.addAttribute("commentsCount", iCacheService.getCommentNum());
        model.addAttribute("user", iCacheService.getAdminInfo());
        return "/index";
    }


    @GetMapping("/blog")
    public String blog() {
        return "/blog";
    }

    @GetMapping("/blog/{blId}")
    public String blog(@PathVariable Long blId, Model model) {
        Blog blog = iBlogService.findFullById(blId);
        iBlogService.addViews(blog);
        if (!blog.getPublished()) {
            throw new RuntimeException("无效资源！");
        }
        model.addAttribute("blog", blog);
        model.addAttribute("user", iCacheService.getAdminInfo());
        return "/blog";
    }


}
