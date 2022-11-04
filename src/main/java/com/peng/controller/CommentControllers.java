package com.peng.controller;


import com.alibaba.fastjson.JSON;
import com.peng.aspect.MyLog;
import com.peng.entity.Blog;
import com.peng.entity.Comment;
import com.peng.service.IBlogService;
import com.peng.service.ICommentService;
import com.peng.util.IpUtil;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Controller
public class CommentControllers {

    @Autowired
    private ICommentService iCommentService;


    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @MyLog
    @GetMapping("/comments/{blId}")
    public String comments(@PathVariable Long blId, Model model) {
        Blog blog = iBlogService.findFullById(blId);
        if (!blog.getPublished()) {
            throw new RuntimeException("无效资源！");
        }
        model.addAttribute("blog", blog);
        return "blog :: commentList";
    }

    @MyLog
    @PostMapping("/comments")
    public String postComments(Comment comment, HttpServletRequest request) {
        //保存评论
        if (comment.getParentId() <= -1)
            comment.setParentId(null);
        String ipAddress = IpUtil.getIpAddress(request);
        comment.setIpAddress(ipAddress);
        iCommentService.saveOrUpdate(comment);
        rabbitTemplate.convertAndSend("msg-event-exchange", "msg.wx-pn", formatWxMsg(comment));
        return "redirect:/comments/" + comment.getBlId();
    }

    private String formatWxMsg(Comment comment) {
        StringBuilder sb = new StringBuilder();
        sb.append("发送人:").append(comment.getName()).append(";");
        if (comment.getParentId() != null) {
            Comment parent = iCommentService.getById(comment.getParentId());
            if (parent != null) {
                sb.append("接收人:").append(parent.getName()).append(";");
            }
        }
        sb.append("内容:").append(comment.getContent()).append(";");
        return sb.toString();
    }

}
