package com.peng.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.entity.Tag;

import java.util.List;

public interface ITagService extends IService<Tag> {
      List<Tag> getIndexTags();
//    Tag findByid(Integer ta_id);
//    List<Tag> findall();
//    List<Tag> findAllPro();
//    boolean addORedit(Tag tag);
//    boolean deleteByid(Integer ta_id);
}
