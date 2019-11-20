package com.peng.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 *id,名称
 * */
@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
public class Tag implements Serializable {
    private Integer ta_id;
    private String name;
    //------------------------------------------------------------------------------------------------------------------
    private List<Blog> blogs;

    public Tag(){}
    public Tag(Integer ta_id){this.ta_id=ta_id;}
    @Override
    public String toString() {
        return "Tag{" +
                "ta_id=" + ta_id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }

    public Integer getTa_id() {
        return ta_id;
    }

    public void setTa_id(Integer ta_id) {
        this.ta_id = ta_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
