package com.peng.controller;


import com.peng.domain.Type;
import com.peng.domain.User;
import com.peng.service.Impl.BlogService;
import com.peng.service.Impl.TypeService;
import com.peng.service.Impl.UserService;
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
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/types/{ty_id}")
    public String types(@PathVariable Integer ty_id, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model){

        List<Type> types = typeService.findallPro();
        if(ty_id==-1){

            ty_id =types.get(0).getTy_id();

        }
        User user=userService.findByid(1);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.findsByType(pageNum,5,ty_id));
        model.addAttribute("activeTypeId",ty_id);
        model.addAttribute("user",user);

        return "types";

    }

}
