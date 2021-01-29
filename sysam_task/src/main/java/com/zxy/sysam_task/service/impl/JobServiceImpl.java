package com.zxy.sysam_task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxy.sysam_task.config.QuartzJobManager;
import com.zxy.sysam_task.entity.Job;
import com.zxy.sysam_task.dao.JobMapper;
import com.zxy.sysam_task.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxy.sysam_task.utils.BaseResult;
import com.zxy.sysam_task.utils.JobOperateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * @since 2021-01-28
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    //默认任务组
    private static String groupName = "DEFAULT";

    @Autowired
    QuartzJobManager quartzJobManager;

    @Autowired
    JobMapper jobMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult add(Job job) {
        String pageName = "com.zxy.sysam_task.task.";
        job.setStatus(0);
        job.setCreateTime(new Date());
        int result = jobMapper.insert(job);
        Map<String, Object> map = new HashMap<>();
        map.put("methodName", job.getMethodName());
        map.put("params", job.getParams());
        try {
//            quartzJobManager.addJob(Class.forName(pageName + job.getBeanName()), job.getJobId(), groupName, job.getCronExpression(), map);
            quartzJobManager.addJob(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResult(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult delete(Job job) {
        int result = jobMapper.deleteById(job.getJobId());
        quartzJobManager.deleteJob(job.getJobId(), groupName);
        return new BaseResult(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult selectJobInfos(Job job) {
        QueryWrapper<Job> wrapper = new QueryWrapper<>();
        List<Job> list = jobMapper.selectList(wrapper);
        return new BaseResult(list);
    }

    @Override
    public BaseResult pauseJob(List<Job> jobs) {
        for (Job job : jobs) {
            job.setStatus((int) JobOperateEnum.PAUSE.getValue());
            jobMapper.updateById(job);
            quartzJobManager.pauseJob(job.getJobId(), groupName);
        }
        return new BaseResult(1);
    }

    @Override
    public BaseResult resumeJob(List<Job> jobs) {
        for (Job job : jobs) {
            job.setStatus((int) JobOperateEnum.START.getValue());
            jobMapper.updateById(job);
            quartzJobManager.resumeJob(job.getJobId(), groupName);
        }
        return new BaseResult(1);

    }

}
