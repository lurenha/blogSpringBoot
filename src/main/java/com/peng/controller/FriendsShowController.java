package com.peng.controller;



import com.peng.service.Impl.FriendService;
import com.peng.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FriendsShowController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private UserService userService;

    @GetMapping("/friends")
    public String friends(Model model){
        model.addAttribute("friends",friendService.findpage(0,Integer.MAX_VALUE).getList());
        model.addAttribute("user",userService.findByid(1));
        return "friends";

    }

}
