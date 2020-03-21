package com.peng.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.entity.Friend;
import com.peng.mapper.FriendMapper;
import com.peng.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {
//    @Autowired
//    private FriendDao friendDao;
//
//    @Override
//    public Friend findByid(Integer fr_id) {
//        return friendDao.findFriendById(fr_id);
//    }
//
//    @Override
//    public PageInfo<Friend> findpage(Integer pageNum, Integer pagesize) {
//        PageHelper.startPage(pageNum, pagesize);
//        List<Friend> friendList = friendDao.findAllFriend();
//        PageInfo<Friend> friends = new PageInfo<>(friendList);
//        return friends;
//
//    }
//
//    @Override
//    public List<Friend> findall() {
//        return friendDao.findAllFriend();
//    }
//
//    @Override
//    public boolean addORedit(Friend friend) {
//        Integer fr_id = friend.getFr_id();
//        if (fr_id != null) {//更新
//            friendDao.updateFriend(friend);
//        } else {//添加
//            friendDao.addFriend(friend);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean deleteByid(Integer fr_id) {
//        friendDao.deleteFriendById(fr_id);
//        return true;
//    }
}
