package com.zxy.sysam_task.service;

import com.zxy.sysam_task.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxy.sysam_task.utils.BaseResult;

/**
 * <p>
 * 定时任务表 服务类
 * </p>
 *
 * @author jibl
 * @since 2021-01-28
 */
public interface JobService extends IService<Job> {

    BaseResult add(Job job);

    BaseResult delete(Job job);

    BaseResult selectJobInfos(Job job);

    BaseResult pauseJob(Job job);

    BaseResult resumeJob(Job job);
}
