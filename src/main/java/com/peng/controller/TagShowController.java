package com.peng.controller;



import com.peng.aspect.MyLog;
import com.peng.entity.Tag;
import com.peng.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {


    @Autowired
    private ICacheService iCacheService;

    @MyLog
    @GetMapping("/tags/{taId}")
    public String tags(@PathVariable Long taId, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model){

        List<Tag> tags = iCacheService.getIndexTags();
        if(taId==-1){
            taId = tags.get(0).getTaId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",iCacheService.getPageByTag(pageNum,taId));
        model.addAttribute("activeTagId",taId);
        model.addAttribute("user",iCacheService.getAdminInfo());
        return "tags";
    }


}
