//package com.peng.service.Impl;
//
//import com.peng.dao.IPAdressDao;
//import com.peng.domain.IPAddress;
//import com.peng.service.IIpAddressService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service("IIpAddressService")
//public class IpAddressService implements IIpAddressService {
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
//
//
//}
