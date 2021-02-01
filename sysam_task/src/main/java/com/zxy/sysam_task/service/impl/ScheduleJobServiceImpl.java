package com.zxy.sysam_task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.sysam_task.entity.JobLog;
import com.zxy.sysam_task.utils.QuartzJobManager;
import com.zxy.sysam_task.entity.ScheduleJob;
import com.zxy.sysam_task.dao.ScheduleJobMapper;
import com.zxy.sysam_task.service.ScheduleJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxy.sysam_task.utils.BaseResult;
import com.zxy.sysam_task.utils.JobOperateEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务表 服务实现类
 * </p>
 *
 * @author jibl
 * @since 2021-02-01
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {
    //默认任务组
    private static String groupName = "DEFAULT";

    @Autowired
    QuartzJobManager quartzJobManager;

    @Autowired
    ScheduleJobMapper jobMapper;

    //任务名称前缀
    private static final String JOB_NAME_PREFIX = "TASK_";

    /**
     * 获取任务名称JobName
     */
    private static String getJobName(Long jobId) {
        return JOB_NAME_PREFIX + jobId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult add(ScheduleJob scheduleJob) {
        String pageName = "com.zxy.sysam_task.task.";
        scheduleJob.setStatus(0);
        scheduleJob.setCreateTime(new Date());
        int result = jobMapper.insert(scheduleJob);
        Map<String, Object> map = new HashMap<>();
        map.put("methodName", scheduleJob.getMethodName());
        map.put("params", scheduleJob.getParams());
        try {
//            quartzJobManager.addJob(Class.forName(pageName + job.getBeanName()), job.getJobId(), groupName, job.getCronExpression(), map);
            quartzJobManager.addJob(scheduleJob);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult delete(ScheduleJob scheduleJob) {
        int result = jobMapper.deleteById(scheduleJob.getJobId());
        quartzJobManager.deleteJob(getJobName(scheduleJob.getJobId()), groupName);
        return new BaseResult(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult selectJobInfos(HttpServletRequest request, ScheduleJob scheduleJob) {

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        IPage<ScheduleJob> jobPage = new Page<>(currentPage, pageSize);//参数一是当前页，参数二是每页个数
        QueryWrapper<ScheduleJob> queryWrapper = new QueryWrapper<ScheduleJob>();
        if (StringUtils.isNotBlank(scheduleJob.getBeanName())) {
            queryWrapper.like("BEAN_NAME", scheduleJob.getBeanName());
        }
        if (StringUtils.isNotBlank(scheduleJob.getMethodName())) {
            queryWrapper.like("METHOD_NAME", scheduleJob.getMethodName());
        }
        if (scheduleJob.getStatus() != null) {
            queryWrapper.eq("STATUS", scheduleJob.getStatus());
        }
        queryWrapper.orderByDesc("CREATE_TIME");
        jobPage = jobMapper.selectPage(jobPage, queryWrapper);
        return new BaseResult(jobPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult pauseJob(List<ScheduleJob> scheduleJobs) {
        for (ScheduleJob scheduleJob : scheduleJobs) {
            scheduleJob.setStatus((int) JobOperateEnum.PAUSE.getValue());
            jobMapper.updateById(scheduleJob);
            quartzJobManager.pauseJob(getJobName(scheduleJob.getJobId()), groupName);
        }
        return new BaseResult(1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult resumeJob(List<ScheduleJob> scheduleJobs) {
        for (ScheduleJob scheduleJob : scheduleJobs) {
            scheduleJob.setStatus((int) JobOperateEnum.START.getValue());
            jobMapper.updateById(scheduleJob);
            quartzJobManager.resumeJob(getJobName(scheduleJob.getJobId()), groupName);
        }
        return new BaseResult(1);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult updateJobInfo(ScheduleJob scheduleJob) {
        jobMapper.updateById(scheduleJob);
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("job", scheduleJob);
        quartzJobManager.updateJob(getJobName(scheduleJob.getJobId()), groupName, scheduleJob.getCronExpression(),argMap);
        return new BaseResult(1);
    }
}
