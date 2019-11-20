package com.peng.controller;


import com.peng.domain.Comment;
import com.peng.domain.IPAddress;
import com.peng.domain.JsonResult.JsonResult;
import com.peng.service.Impl.BlogService;
import com.peng.service.Impl.CommentService;
import com.peng.service.Impl.IpAddressService;
import com.peng.util.IPUtile;
import com.peng.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


@Controller
public class CommentControllers {

    @Autowired
    private CommentService commentService;

    @Autowired
    private IpAddressService ipAddressService;

    @Autowired
    private BlogService blogService;

//    @Value("${comment.avatar}")
//    private String avatar;

    @GetMapping("/comments/{blog_id}")
    public String comments(@PathVariable Integer blog_id, Model model) {
        model.addAttribute("blog", blogService.findByidPro(blog_id));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String postcomments(Comment comment, HttpServletRequest request) {
//        //是否是管理员
//        String token = request.getHeader("Peng-Token");
//        comment.setAdminComment(false);
//        if (token != null) {
//            if (TokenUtil.verify(token)) {
//                comment.setAdminComment(true);
//            }
//        }
        //IP相关
        String ipAddr = IPUtile.getIpAddr(request);
        IPAddress ipAddress = ipAddressService.findByAdress(ipAddr);
        if (ipAddress == null) {
            ipAddressService.addORedit(new IPAddress(ipAddr, "http://b-ssl.duitang.com/uploads/item/201802/21/20180221223815_xkkyq.jpg"));
            ipAddress = ipAddressService.findByAdress(ipAddr);
        }
        //保存评论
        if (comment.getParent_id() <= -1)
            comment.setParent_id(null);
        comment.setIp_id(ipAddress.getIp_id());
        commentService.addORedit(comment);
        return "redirect:/comments/" + comment.getBl_id();
    }

}
