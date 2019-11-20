package com.peng.dao;

import com.peng.domain.IPAddress;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface IPAdressDao {
    //根据id查询
    @Select("select * from t_ip where ip_id= #{ip_id}")
    IPAddress findIPByid(Integer ip_id);

    //根据地址查询
    @Select("select * from t_ip where address= #{address}")
    IPAddress findIPByAddress(String address);

    //根据id删除
    @Delete("delete from t_ip where ip_id = #{ip_id}")
    int deleteIPbyid(Integer ip_id);

    //更新
    @Update("update Tag set address=#{address},avatar=#{avatar} where ip_id=#{ip_id};" )
    int updateIP(IPAddress ipAddress);

    //增加
    @Insert("insert into t_ip (address,avatar) values(#{address},#{avatar});")
    @Options(useGeneratedKeys = true,keyProperty = "ip_id",keyColumn = "ip_id")
    int addIP(IPAddress ipAddress);

    //查询所有
    @Select("select * from t_ip")
    List<IPAddress> findallIP();

}
