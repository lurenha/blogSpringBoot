package com.peng.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.peng.entity.Tag;

import java.util.List;

public interface ITagService extends IService<Tag> {
      List<Tag> getIndexTags();

}
