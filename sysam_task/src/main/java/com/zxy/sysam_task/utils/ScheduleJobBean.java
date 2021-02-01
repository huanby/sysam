package com.zxy.sysam_task.utils;

import com.zxy.sysam_task.entity.JobLog;
import com.zxy.sysam_task.entity.ScheduleJob;
import com.zxy.sysam_task.service.JobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_task.utils
 * @ClassName: ScheduleJobBean
 * @Author: jibl
 * @Description:
 * @Date: 2021/2/1 9:29
 * @Version: 1.0
 */
@Slf4j
public class ScheduleJobBean implements Job {

    private final ThreadPoolTaskExecutor scheduleJobExecutorService = SpringContextUtil.getBean("scheduleJobExecutorService", ThreadPoolTaskExecutor.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map map = context.getTrigger().getJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob) map.get("job");
        // 获取spring bean
        JobLogService scheduleJobLogService = SpringContextUtil.getBean(JobLogService.class);
        //封装
        JobLog jobLog = new JobLog();
        jobLog.setJobId(scheduleJob.getJobId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
            log.info("任务准备执行，任务ID：{}", scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(),
                    scheduleJob.getParams());
            Future<?> future = scheduleJobExecutorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes(times);
            // 任务状态 0：成功 1：失败
            jobLog.setStatus(JobLog.JOB_SUCCESS);
            log.info("任务执行完毕，任务ID：{} 总共耗时：{} 毫秒", scheduleJob.getJobId(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
            long times = System.currentTimeMillis() - startTime;
            jobLog.setTimes(times);
            // 任务状态 0：成功 1：失败
            jobLog.setStatus(JobLog.JOB_FAIL);
            jobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.save(jobLog);
        }
    }
}
