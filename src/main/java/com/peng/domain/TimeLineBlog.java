package com.peng.domain;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
public class TimeLineBlog implements Serializable {
    private Integer bl_id;
    private String title;
    private String date;
    private String month;

    @Override
    public String toString() {
        return "TimeLineBlog{" +
                "bl_id=" + bl_id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", month='" + month + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
