package com.peng.controller;



import com.peng.aspect.MyCache;
import com.peng.entity.Blog;
import com.peng.service.IBlogService;
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

//    @Autowired
//    private TypeService typeService;
//
//    @Autowired
//    private TagService tagService;
//
//    @Autowired
//    private CommentService commentService;
//
//    @Autowired
//    private UserService userService;


    @GetMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,@RequestParam(required=false, value = "title")String title, Model model ) {
//        User user = userService.findByid(1);
        model.addAttribute("page", iBlogService.getListByPage(pageNum,5));
//        model.addAttribute("types", typeService.findallPro());
//        model.addAttribute("tags", tagService.findAllPro());
//        model.addAttribute("blogsCount", blogService.getPusBlogs());
//        model.addAttribute("typesCount", typeService.findall().size());
//        model.addAttribute("tagsCount", tagService.findall().size());
//        model.addAttribute("commentsCount", commentService.findpage(0, Integer.MAX_VALUE).getSize());
//        model.addAttribute("user", user);
        return "test/index";
    }


    @GetMapping("/blog")
    public String blog() {
        return "test/detail";
    }
    
    @GetMapping("/blog/{blId}")
    public String blog(@PathVariable Long blId, Model model) {
        Blog blog = iBlogService.getById(blId);
//        blogService.addViews(bl_id);
//        if (!byidPro.getPublished()) {
//            throw new RuntimeException("无效资源！");
//        }
        model.addAttribute("blog", blog);
//        model.addAttribute("user", userService.findByid(1));
        return "test/detail";
    }


}
