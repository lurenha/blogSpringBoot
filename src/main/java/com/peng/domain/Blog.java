package com.peng.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.peng.util.DateUtile;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * id,标题，内容，简介，标志（原创，转载...），是否推荐，评论是否开启，是否发布，创建日期，最后修改日期，访问量，外键：类型id
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
public class Blog implements Serializable {
    private Integer bl_id;
    private String title;
    private String content;
    private String outline;
    private String flag;
    private Boolean recommend;
    private Boolean commentabled;
    private Boolean published;
    private Date creatdate;
    private Date finaldate;
    private Integer views;
    private Integer ty_id;
    //------------------------------------------------------------------------------------------------------------------
    private List<Tag> tags;
    private List<Comment> comments;
    private Type type;
    private List<Integer> tag_ids;


    @Override
    public String toString() {
        return "Blog{" +
                "bl_id=" + bl_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", outline='" + outline + '\'' +
                ", flag='" + flag + '\'' +
                ", recommend=" + recommend +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", creatdate=" + creatdate +
                ", finaldate=" + finaldate +
                ", views=" + views +
                ", ty_id=" + ty_id +
                ", tags=" + tags +
                ", comments=" + comments +
                ", type=" + type +
                ", tag_ids=" + tag_ids +
                '}';
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public Date getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(Date finaldate) {
        this.finaldate = finaldate;
    }

    public List<Integer> getTag_ids() {
        return tag_ids;
    }

    public void setTag_ids(List<Integer> tag_ids) {
        this.tag_ids = tag_ids;
    }

    public Integer getBl_id() {
        return bl_id;
    }

    public void setBl_id(Integer bl_id) {
        this.bl_id = bl_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Boolean getCommentabled() {
        return commentabled;
    }

    public void setCommentabled(Boolean commentabled) {
        this.commentabled = commentabled;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getTy_id() {
        return ty_id;
    }

    public void setTy_id(Integer ty_id) {
        this.ty_id = ty_id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
