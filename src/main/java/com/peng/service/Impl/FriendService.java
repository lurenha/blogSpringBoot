package com.peng.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.dao.FriendDao;

import com.peng.domain.Friend;
import com.peng.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("IFriendService")
public class FriendService implements IFriendService {
    @Autowired
    private FriendDao friendDao;

    @Override
    public Friend findByid(Integer fr_id) {
        return friendDao.findFriendByid(fr_id);
    }

    @Override
    public PageInfo<Friend> findpage(Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Friend> friendList = friendDao.findAllFriend();
        PageInfo<Friend> friends = new PageInfo<>(friendList);
        return friends;

    }

    @Override
    public List<Friend> findall() {
        return friendDao.findAllFriend();
    }

    @Override
    public boolean addORedit(Friend friend) {
        Integer fr_id = friend.getFr_id();
        if (fr_id != null) {//更新
            friendDao.updateFriend(friend);
        } else {//添加
            friendDao.addFriend(friend);
        }
        return true;
    }

    @Override
    public boolean deleteByid(Integer fr_id) {
        friendDao.deleteFriendByid(fr_id);
        return true;
    }
}
