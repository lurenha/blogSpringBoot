package com.peng.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.peng.util.DateUtile;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * id,昵称，邮箱，内容，头像，创建时间，外键：博客id 父节点id
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
public class Comment implements Serializable {
    private Integer co_id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date creatdate;
    private boolean adminComment;
    private Integer bl_id;
    private  Integer parent_id;
    private Integer ip_id;

    //------------------------------------------------------------------------------------------------------------------
    private Blog blog;
    private List<Comment> childcoments;
    private Comment parent;
    private IPAddress ipAddress;




    @Override
    public String toString() {
        return "Comment{" +
                "co_id=" + co_id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", creatdate=" + creatdate +
                ", adminComment=" + adminComment +
                ", bl_id=" + bl_id +
                ", parent_id=" + parent_id +
                ", ip_id=" + ip_id +
                ", blog=" + blog +
                ", childcoments=" + childcoments +
                ", parent=" + parent +
                ", ipAddress=" + ipAddress +
                '}';
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }



    public Integer getIp_id() {
        return ip_id;
    }

    public void setIp_id(Integer ip_id) {
        this.ip_id = ip_id;
    }

    public IPAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public List<Comment> getChildcoments() {
        return childcoments;
    }

    public void setChildcoments(List<Comment> childcoments) {
        this.childcoments = childcoments;
    }

    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreatdate() {
        return DateUtile.getTime(creatdate);
    }

    public void setCreatdate(String creatdate) throws ParseException {
        this.creatdate =  DateUtile.setTime(creatdate);
    }

    public Integer getBl_id() {
        return bl_id;
    }

    public void setBl_id(Integer bl_id) {
        this.bl_id = bl_id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
