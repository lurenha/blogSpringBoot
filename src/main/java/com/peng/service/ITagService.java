package com.peng.service;


import com.peng.domain.Tag;

import java.util.List;

public interface ITagService {
    Tag findByid(Integer ta_id);
    List<Tag> findall();
    List<Tag> findallPro();
    boolean addORedit(Tag tag);
    boolean deleteByid(Integer ta_id);
}
