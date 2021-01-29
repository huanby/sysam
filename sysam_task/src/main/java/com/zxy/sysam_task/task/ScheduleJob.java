package com.zxy.sysam_task.task;

import com.zxy.sysam_task.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_task.config
 * @ClassName: TestQuartz
 * @Author: jibl
 * @Description:
 * @Date: 2021/1/25 11:10
 * @Version: 1.0
 */
@Slf4j
public class ScheduleJob implements Job {

    private final ThreadPoolTaskExecutor scheduleJobExecutorService = SpringContextUtil.getBean("scheduleJobExecutorService", ThreadPoolTaskExecutor.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        Job scheduleJob = (Job) context.getMergedJobDataMap().get(Job.JOB_PARAM_KEY);

//        // 获取spring bean
//        IJobLogService scheduleJobLogService = SpringContextUtil.getBean(IJobLogService.class);
//
//        JobLog jobLog = new JobLog();
//        jobLog.setJobId(scheduleJob.getJobId());
//        jobLog.setBeanName(scheduleJob.getBeanName());
//        jobLog.setMethodName(scheduleJob.getMethodName());
//        jobLog.setParams(scheduleJob.getParams());
//        jobLog.setCreateTime(new Date());

        Map map = context.getTrigger().getJobDataMap();
        com.zxy.sysam_task.entity.Job scheduleJob = (com.zxy.sysam_task.entity.Job) map.get("job");
        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
//            log.info("任务准备执行，任务ID：{}", scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(),
                    scheduleJob.getParams());
            Future<?> future = scheduleJobExecutorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
//            jobLog.setTimes(times);
            // 任务状态 0：成功 1：失败
//            jobLog.setStatus(JobLog.JOB_SUCCESS);

            log.info("任务执行完毕，任务ID：{} 总共耗时：{} 毫秒", scheduleJob.getJobId(), times);
        } catch (Exception e) {
//            log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
            long times = System.currentTimeMillis() - startTime;
//            jobLog.setTimes(times);
            // 任务状态 0：成功 1：失败
//            jobLog.setStatus(JobLog.JOB_FAIL);
//            jobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
//            scheduleJobLogService.saveJobLog(jobLog);
        }
    }

}

