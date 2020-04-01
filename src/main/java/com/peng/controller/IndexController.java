package com.peng.controller;



import com.peng.aspect.MyLog;
import com.peng.entity.Blog;
import com.peng.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class IndexController {

    @Autowired
    private IBlogService iBlogService;
    @Autowired
    private ICacheService iCacheService;

    @MyLog
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
        return "index";
    }


    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @MyLog
    @GetMapping("/blog/{blId}")
    public String blog(@PathVariable Long blId, Model model) {
        Blog blog = iBlogService.findFullById(blId);
        iBlogService.addViews(blId);
        if (!blog.getPublished()) {
            throw new RuntimeException("无效资源！");
        }
        model.addAttribute("blog", blog);
        model.addAttribute("user", iCacheService.getAdminInfo());
        return "blog";
    }


}
