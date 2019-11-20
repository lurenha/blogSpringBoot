package com.peng.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 *id,名称,职业,描述,标志,网址
 * */
@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
public class Friend implements Serializable {
    private Integer fr_id;
    private String nickname;
    private String description;
    private String website;
    private Integer flag;


    @Override
    public String toString() {
        return "Friend{" +
                "fr_id=" + fr_id +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getFr_id() {
        return fr_id;
    }

    public void setFr_id(Integer fr_id) {
        this.fr_id = fr_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
