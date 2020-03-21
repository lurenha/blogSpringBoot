//package com.peng.controller.Admin;
//
//import com.peng.domain.Friend;
//import com.peng.domain.JsonResult.JsonResult;
//import com.peng.service.IFriendService;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin/friend")
//public class FriendController {
//    @Autowired
//    private IFriendService iFriendService;
//
//    @RequiresPermissions("friend:addORedit")
//    @RequestMapping(path = "/addORedit", method = RequestMethod.POST)
//    public JsonResult addORedit_friend(Friend friend) {
//        boolean bool = iFriendService.addORedit(friend);
//        if(bool){
//            return new JsonResult(20000, "提交成功!", bool);
//        }else {
//            return new JsonResult(50001, "提交失败!", bool);
//        }
//
//    }
//
//
//    @RequiresPermissions("friend:delete")
//    @RequestMapping(path = "/delete/{idNum}", method = RequestMethod.POST)
//    public JsonResult delete_friend(@PathVariable("idNum") Integer fr_id) {
//        boolean bool = iFriendService.deleteByid(fr_id);
//        if(bool){
//            return new JsonResult(20000, "提交成功!", bool);
//        }else {
//            return new JsonResult(50001, "提交失败!", bool);
//        }
//    }
//
//    @RequiresPermissions("friend:find")
//    @RequestMapping(path = "/find/{idNum}", method = RequestMethod.POST)
//    public JsonResult find_friend(@PathVariable("idNum") Integer fr_id) {
//        Friend friend = iFriendService.findByid(fr_id);
//        return new JsonResult(20000, "ok", friend);
//    }
//
//    @RequiresPermissions("friend:list")
//    @RequestMapping(path = "/list", method = RequestMethod.POST)
//    public JsonResult list_friend() {
//        List<Friend> allType = iFriendService.findall();
//        return new JsonResult(20000, "ok", allType);
//    }
//}
