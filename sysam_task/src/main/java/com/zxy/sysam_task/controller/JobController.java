package com.zxy.sysam_task.controller;


import com.zxy.sysam_task.config.QuartzJobManager;
import com.zxy.sysam_task.config.TestQuartz;
import com.zxy.sysam_task.entity.Job;
import com.zxy.sysam_task.utils.JobOperateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 定时任务表 前端控制器
 * </p>
 *
 * @author jibl
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    QuartzJobManager quartzJobManager;

    @GetMapping("/add")
    public void add(HttpServletRequest request, Job job) {
        job.setGroup("test");
        job.setBeanName("TestQuartz");
        job.setMethodName("ddd");
        job.setParams("hello");
        job.setCronExpression("0 0/1 * * * ?");
        job.setCreateTime(new Date());
        job.setStatus(0);
        job.setRemark("备注.");
        //任务名称
        String name = request.getParameter("name");
        //任务组名称
        String groupName = "task";
        //cron表达式
        String cron = "0 0/1 * * * ?";
        //需要给任务携带的参数
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("sex", "0");
        quartzJobManager.addJob(TestQuartz.class, name, groupName, cron, map);
    }

    @GetMapping("/del")
    public void del(HttpServletRequest request) {
        String name = request.getParameter("name");
        String groupName = "task";
        quartzJobManager.deleteJob(name, groupName);
    }
}

