package com.peng.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.peng.util.DateUtile;

import javax.rmi.CORBA.Util;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 *id,昵称，用户名，密码，邮箱，权限，简介，所在地，微信，扣扣
 * */
@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
public class User implements Serializable {
    private Integer us_id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private Integer power;
    private String about;
    private String location;
    private String vx;
    private String qq;
    private Date finaldate;
    private String avatar;
    private String[] roles;

    @Override
    public String toString() {
        return "User{" +
                "us_id=" + us_id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", power=" + power +
                ", about='" + about + '\'' +
                ", location='" + location + '\'' +
                ", vx='" + vx + '\'' +
                ", qq='" + qq + '\'' +
                ", finaldate=" + finaldate +
                ", avatar='" + avatar + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }

    public String getFinaldate() {
        return DateUtile.getTime(finaldate);
    }

    public void setFinaldate(Date finaldate) {
        this.finaldate = finaldate;
    }

    public Integer getUs_id() {
        return us_id;
    }

    public void setUs_id(Integer us_id) {
        this.us_id = us_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVx() {
        return vx;
    }

    public void setVx(String vx) {
        this.vx = vx;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
