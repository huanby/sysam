package com.sysam.sysam_task.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sysam.sysam_task.entity.JobLog;
import com.sysam.sysam_task.entity.ScheduleJob;
import com.sysam.sysam_task.service.JobLogService;
import com.sysam.sysam_task.utils.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 调度日志表 前端控制器
 * </p>
 *
 * @author jibl
 * @since 2021-02-01
 */
@Api(tags = "定时任务调度日志")
@RestController
@RequestMapping("/joblog")
public class JobLogController {

    @Autowired
    JobLogService jobLogService;

    @ApiOperation("调度日志列表")
    @GetMapping("/list")
    public BaseResult selectJobInfos(HttpServletRequest request, JobLog jobLog) {
        return jobLogService.selectJobLogInfos(request, jobLog);
    }


    @ApiOperation("调度日志批量删除")
    @PostMapping("/batchDelete")
    public BaseResult batchDelete(@RequestBody String params) {
        JSONObject json = JSONObject.parseObject(params);
        List<String> joblogs = JSON.parseArray(json.getJSONArray("ids").toJSONString(), String.class);
        jobLogService.removeByIds(joblogs);
        return new BaseResult(1);
    }


    @ApiOperation("调度日志删除")
    @GetMapping("/delete")
    public BaseResult delete(long id) {
        jobLogService.removeById(id);
        return new BaseResult(1);
    }

}

