package com.zxy.sysam_task.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxy.sysam_task.config.QuartzJobManager;
import com.zxy.sysam_task.entity.Job;
import com.zxy.sysam_task.service.JobService;
import com.zxy.sysam_task.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 定时任务表 前端控制器
 * </p>
 *
 * @author jibl
 * @since 2021-01-28
 */
@RestController
@RequestMapping("/job")
public class JobController {

    //默认任务组
    private static String groupName = "DEFAULT";

    @Autowired
    QuartzJobManager quartzJobManager;

    @Autowired
    JobService jobService;

    @GetMapping("/add")
    public BaseResult add(Job job) {
        return jobService.add(job);
    }


    @GetMapping("/delete")
    public BaseResult delete(Job job) {
        return jobService.delete(job);
    }


    @GetMapping("/list")
    public BaseResult selectJobInfos(Job job) {
        return jobService.selectJobInfos(job);
    }

    @GetMapping("/pauseJob")
    public BaseResult pauseJob(Job  job) {
        return  jobService.pauseJob(job);
    }


    @GetMapping("/resumeJob")
    public BaseResult resumeJob(Job job) {
        return  jobService.resumeJob(job);
    }


}

