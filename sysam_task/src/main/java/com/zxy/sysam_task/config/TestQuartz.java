package com.zxy.sysam_task.config;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_task.config
 * @ClassName: TestQuartz
 * @Author: jibl
 * @Description:
 * @Date: 2021/1/25 11:10
 * @Version: 1.0
 */
@Component
public class TestQuartz implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //获取任务名
        String taskName = context.getJobDetail().getKey().getName();
         Map map = context.getJobDetail().getJobDataMap();
        //处理执行任务之后的业务
        System.out.println("测试定时器3秒执行一次..................");
    }
}

