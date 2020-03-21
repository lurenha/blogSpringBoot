//package com.peng.controller;
//
//
//import com.peng.domain.Tag;
//import com.peng.domain.User;
//import com.peng.service.Impl.BlogService;
//import com.peng.service.Impl.TagService;
//import com.peng.service.Impl.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//public class TagShowController {
//
//    @Autowired
//    private TagService tagService;
//
//    @Autowired
//    private BlogService blogService;
//
//    @Autowired
//    private UserService userService;
//
//
//    @GetMapping("/tags/{ta_id}")
//    public String tags(@PathVariable Integer ta_id, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model){
//
//        List<Tag> tags = tagService.findAllPro();
//        if(ta_id==-1){
//            ta_id = tags.get(0).getTa_id();
//        }
//        User user=userService.findByid(1);
//        model.addAttribute("tags",tags);
//        model.addAttribute("page",blogService.findsByTag(pageNum,5,ta_id));
//        model.addAttribute("activeTagId",ta_id);
//        model.addAttribute("user",user);
//        return "tags";
//    }
//
//
//}
