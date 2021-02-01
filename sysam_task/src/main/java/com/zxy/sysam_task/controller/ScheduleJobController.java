package com.zxy.sysam_task.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxy.sysam_task.entity.JobLog;
import com.zxy.sysam_task.utils.QuartzJobManager;
import com.zxy.sysam_task.entity.ScheduleJob;
import com.zxy.sysam_task.service.ScheduleJobService;
import com.zxy.sysam_task.utils.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 定时任务表 前端控制器
 * </p>
 *
 * @author jibl
 * @since 2021-02-01
 */
@Api(tags = "定时任务")
@RestController
@RequestMapping("/job")
@CrossOrigin
public class ScheduleJobController {
    //默认任务组
    private static String groupName = "DEFAULT";

    @Autowired
    QuartzJobManager quartzJobManager;

    @Autowired
    ScheduleJobService jobService;

    @ApiOperation("定时任务添加")
    @GetMapping("/add")
    public BaseResult add(ScheduleJob scheduleJob) {
        return jobService.add(scheduleJob);
    }


    @ApiOperation("定时任务删除")
    @GetMapping("/delete")
    public BaseResult delete(ScheduleJob scheduleJob) {
        return jobService.delete(scheduleJob);
    }

    @ApiOperation("定时任务查询")
    @GetMapping("/list")
    public BaseResult selectJobInfos(HttpServletRequest request, ScheduleJob scheduleJob) {
        return jobService.selectJobInfos(request,scheduleJob);
    }

    @ApiOperation("定时任务更新")
    @GetMapping("/update")
    public BaseResult updateJobInfo(ScheduleJob scheduleJob) {
        return jobService.updateJobInfo(scheduleJob);
    }


    @ApiOperation("定时任务暂停")
    @PostMapping("/pauseJob")
    public BaseResult pauseJob(@RequestBody String params) {
        JSONObject json = JSONObject.parseObject(params);
        List<ScheduleJob> jobs = JSON.parseArray(json.getJSONArray("list").toJSONString(), ScheduleJob.class);
        return jobService.pauseJob(jobs);
    }

    @ApiOperation("定时任务恢复")
    @PostMapping("/resumeJob")
    public BaseResult resumeJob(@RequestBody String params) {
        JSONObject json = JSONObject.parseObject(params);
        List<ScheduleJob> jobs = JSON.parseArray(json.getJSONArray("list").toJSONString(), ScheduleJob.class);
        return jobService.resumeJob(jobs);
    }

    @ApiOperation("定时任务批量删除")
    @PostMapping("/batchDelete")
    public BaseResult batchDelete(@RequestBody String params) {
        JSONObject json = JSONObject.parseObject(params);
        List<ScheduleJob> jobs = JSON.parseArray(json.getJSONArray("list").toJSONString(), ScheduleJob.class);
        for (ScheduleJob scheduleJob : jobs) {
            jobService.delete(scheduleJob);
        }
        return new BaseResult(1);
    }
}

