package com.peng.controller.Admin;



import com.peng.domain.JsonResult.JsonResult;
import com.peng.domain.Tag;
import com.peng.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/tag")
public class TagController {
    @Autowired
    private ITagService tagService;
    @RequestMapping(path = "/addORedit", method = RequestMethod.POST)
    public JsonResult addORedit_tag(Tag tag) {
        boolean bool = tagService.addORedit(tag);
        if(bool){
            return new JsonResult(20000, "提交成功!", bool);
        }else {
            return new JsonResult(50001, "提交失败!", bool);
        }

    }


    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
    public JsonResult delete_tag(@PathVariable("idNum") Integer ta_id) {
        boolean bool = tagService.deleteByid(ta_id);
        if(bool){
            return new JsonResult(20000, "提交成功!", bool);
        }else {
            return new JsonResult(50001, "提交失败!", bool);
        }
    }


    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
    public JsonResult find_tag(@PathVariable("idNum") Integer ta_id) {
        Tag tag = tagService.findByid(ta_id);
        return new JsonResult(20000, "ok", tag);
    }


    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public JsonResult list_tag() {
        List<Tag> allType = tagService.findall();
        return new JsonResult(20000, "ok", allType);
    }
}
