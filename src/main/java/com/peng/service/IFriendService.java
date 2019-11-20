package com.peng.service;

import com.github.pagehelper.PageInfo;
import com.peng.domain.Friend;

import java.util.List;

public interface IFriendService {
    Friend findByid(Integer fr_id);
    PageInfo<Friend> findpage(Integer pageNum, Integer pagesize);
    List<Friend> findall();
    boolean addORedit(Friend friend);
    boolean deleteByid(Integer fr_id);
}
