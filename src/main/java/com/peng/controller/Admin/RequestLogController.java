package com.peng.controller.Admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.peng.aspect.MyLog;
import com.peng.entity.RequestLog;
import com.peng.entity.Result.JsonResult;
import com.peng.entity.Result.ResultCode;
import com.peng.entity.Result.ResultUtil;
import com.peng.service.IRequestLogService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/admin/log")
public class RequestLogController {
    @Autowired
    private IRequestLogService iRequestLogService;

    /**
     * 查询日志列表
     */

    @PreAuthorize("hasAuthority('content:requestlog:query')")
    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "beginTime", required = false) String beginTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "ipAddress", required = false) String ipAddress) {
        PageInfo<RequestLog> listByPage = iRequestLogService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<RequestLog>().eq(Strings.isNotBlank(ipAddress), RequestLog::getIpAddress, ipAddress).between(Strings.isNotBlank(beginTime) && Strings.isNotBlank(endTime), RequestLog::getCreateTime, beginTime, endTime).orderByDesc(RequestLog::getCreateTime));
        return ResultUtil.success(listByPage, ResultCode.SUCCESS);
    }

    /**
     * 删除日志
     */
    @MyLog
    @PreAuthorize("hasAuthority('content:requestlog:remove')")
    @GetMapping("/delete/{logIds}")
    public JsonResult remove(@PathVariable Long[] logIds) {

        boolean bool = iRequestLogService.removeByIds(Arrays.asList(logIds));
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }
}
