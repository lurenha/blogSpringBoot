package com.peng.domain;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(value= JsonInclude.Include.NON_NULL)//为NULL的字段不返回为（JSON）
public class IPAddress implements Serializable {
    private Integer ip_id;
    private String avatar;
    private String address;

    @Override
    public String toString() {
        return "IPAddress{" +
                "ip_id=" + ip_id +
                ", avatar='" + avatar + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getIp_id() {
        return ip_id;
    }

    public void setIp_id(Integer ip_id) {
        this.ip_id = ip_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public IPAddress() {
    }
    public IPAddress(String address,String avatar ) {
        this.address=address;
        this.avatar=avatar;
    }
}
