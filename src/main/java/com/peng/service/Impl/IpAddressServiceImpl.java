package com.peng.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.entity.IPAddress;
import com.peng.mapper.IPAddressMapper;
import com.peng.service.IIpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IpAddressServiceImpl extends ServiceImpl<IPAddressMapper, IPAddress> implements IIpAddressService {
//    @Autowired
//    private IPAdressDao ipAdressDao;
//
//    @Override
//    public IPAddress findByid(Integer ip_id) {
//       return ipAdressDao.findIpById(ip_id);
//
//    }
//
//    @Override
//    public IPAddress findByAdress(String address) {
//        return ipAdressDao.findIpByAddress(address);
//    }
//
//    @Override
//    public List<IPAddress> findall() {
//        return  ipAdressDao.findAllIP();
//    }
//
//    @Override
//    public boolean addORedit(IPAddress ipAddress) {
//        Integer ip_id = ipAddress.getIp_id();
//        if (ip_id != null) {//更新
//            ipAdressDao.updateIP(ipAddress);
//        } else {//添加
//            ipAdressDao.addIP(ipAddress);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean deleteByid(Integer ip_id) {
//        ipAdressDao.deleteIpById(ip_id);
//        return true;
//    }


}
