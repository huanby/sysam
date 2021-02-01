package com.zxy.sysam_task.service;

import com.zxy.sysam_task.entity.ScheduleJob;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxy.sysam_task.utils.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 定时任务表 服务类
 * </p>
 *
 * @author jibl
 * @since 2021-02-01
 */
public interface ScheduleJobService extends IService<ScheduleJob> {
    BaseResult add(ScheduleJob job);

    BaseResult delete(ScheduleJob job);

    BaseResult selectJobInfos(HttpServletRequest request, ScheduleJob job);

    BaseResult pauseJob(List<ScheduleJob> job);

    BaseResult resumeJob(List<ScheduleJob> job);

    BaseResult updateJobInfo(ScheduleJob scheduleJob);
}
