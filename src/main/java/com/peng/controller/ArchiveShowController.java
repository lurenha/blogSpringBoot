//package com.peng.controller;
//
//
//import com.peng.service.Impl.BlogService;
//import com.peng.service.Impl.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import java.util.Map;
//
//
//@Controller
//public class ArchiveShowController {
//
//    @Autowired
//    private BlogService blogService;
//    @Autowired
//    private UserService userService;
//
//
//
//    @GetMapping("/archives")
//    public String archivesByYearAndMonth(Model model){
//        Map timeLineMap = blogService.findTimeLine();
//        model.addAttribute("timeLineMap",timeLineMap);
//        model.addAttribute("user",userService.findByid(1));
//        return "archives";
//    }
//
//
//}
