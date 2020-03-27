package com.peng.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.entity.IPAddress;
import com.peng.mapper.IPAddressMapper;
import com.peng.service.IIpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IpAddressServiceImpl extends ServiceImpl<IPAddressMapper, IPAddress> implements IIpAddressService {


}
