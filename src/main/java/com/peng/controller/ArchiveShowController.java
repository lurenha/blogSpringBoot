package com.peng.controller;


import com.peng.aspect.MyLog;
import com.peng.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ArchiveShowController {


    @Autowired
    private ICacheService iCacheService;


    @MyLog
    @GetMapping("/archives")
    public String archivesByYearAndMonth(Model model){
        model.addAttribute("timeLineMap",iCacheService.findTimeLine());
        model.addAttribute("user",iCacheService.getAdminInfo());
        return "archives";
    }


}
