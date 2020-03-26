package com.peng.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peng.aspect.MyCache;
import com.peng.entity.Tag;
import com.peng.mapper.TagMapper;
import com.peng.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getIndexTags() {
        return tagMapper.getIndexTag();
    }



//    @Autowired
//    private TagDao tagDao;
//
//    @Override
//    public Tag findByid(Integer ta_id) {
//        return tagDao.findTagByid(ta_id);
//
//    }
//
//
//    @Override
//    public List<Tag> findall() {
//        List<Tag> tagList = tagDao.findallTag();
//        return tagList;
//    }
//
//
//    @MyCache
//    @Override
//    public List<Tag> findAllPro() {
//        List<Tag> tagList = tagDao.findallTagPro();
//        return tagList;
//    }
//
//    @Override
//    public boolean addORedit(Tag tag) {
//        Integer ta_id = tag.getTa_id();
//        if (ta_id != null) {//更新
//            tagDao.updateTag(tag);
//        } else {//添加
//            tagDao.addTag(tag);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean deleteByid(Integer ta_id) {
//        Tag tag = tagDao.findTagByidPro(ta_id);
//        if (tag.getBlogs() == null || tag.getBlogs().size() == 0) {
//            tagDao.deleteTagbyid(ta_id);
//            return true;
//        } else {
//            return false;
//        }
//
//    }
}
