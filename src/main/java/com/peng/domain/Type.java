package com.peng.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;


/**
 *id,名称
 * */
@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
public class Type implements Serializable {
    private Integer ty_id;
    private String name;
    //------------------------------------------------------------------------------------------------------------------
    private List<Blog> blogs;

    @Override
    public String toString() {
        return "Type{" +
                "ty_id=" + ty_id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }

    public Integer getTy_id() {
        return ty_id;
    }

    public void setTy_id(Integer ty_id) {
        this.ty_id = ty_id;
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
