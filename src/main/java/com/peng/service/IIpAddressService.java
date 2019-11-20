package com.peng.service;

import com.peng.domain.IPAddress;

import java.util.List;

public interface IIpAddressService {
    IPAddress findByid(Integer ip_id);
    IPAddress findByAdress(String address);
    List<IPAddress> findall();
    boolean addORedit(IPAddress ipAddress);
    boolean deleteByid(Integer ip_id);
}
