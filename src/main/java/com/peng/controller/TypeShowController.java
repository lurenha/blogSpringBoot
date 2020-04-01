package com.peng.controller;


import com.peng.aspect.MyLog;
import com.peng.entity.Type;
import com.peng.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TypeShowController {


    @Autowired
    private ICacheService iCacheService;

    @MyLog
    @GetMapping("/types/{tyId}")
    public String types(@PathVariable Long tyId, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model){

        List<Type> types = iCacheService.getIndexTypes();
        if(tyId==-1){
            tyId =types.get(0).getTyId();
        }
        model.addAttribute("types",types);
        model.addAttribute("page",iCacheService.getPageByType(pageNum,tyId));
        model.addAttribute("activeTypeId",tyId);
        model.addAttribute("user",iCacheService.getAdminInfo());

        return "types";

    }

}
