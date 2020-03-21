package com.peng.controller;


import com.peng.domain.Blog;
import com.peng.domain.User;
import com.peng.service.Impl.*;
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
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "0", value = "page") Integer pageNum,@RequestParam(required=false, value = "title")String title, Model model ) {
        User user = userService.findByid(1);
        model.addAttribute("page", blogService.findPubpage(pageNum, 5,title));
        model.addAttribute("types", typeService.findallPro());
        model.addAttribute("tags", tagService.findAllPro());
        model.addAttribute("blogsCount", blogService.getPusBlogs());
        model.addAttribute("typesCount", typeService.findall().size());
        model.addAttribute("tagsCount", tagService.findall().size());
        model.addAttribute("commentsCount", commentService.findpage(0, Integer.MAX_VALUE).getSize());
        model.addAttribute("user", user);
        return "index";
    }


    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/blog/{bl_id}")
    public String blog(@PathVariable Integer bl_id, Model model) {
        Blog byidPro = blogService.findByidPro(bl_id);
        blogService.addViews(bl_id);
        if (!byidPro.getPublished()) {
            throw new RuntimeException("无效资源！");
        }
        model.addAttribute("blog", byidPro);
        model.addAttribute("user", userService.findByid(1));
        return "blog";
    }


}
