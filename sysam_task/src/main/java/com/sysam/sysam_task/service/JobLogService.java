package com.sysam.sysam_task.service;

import com.sysam.sysam_task.entity.JobLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sysam.sysam_task.entity.ScheduleJob;
import com.sysam.sysam_task.utils.BaseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 调度日志表 服务类
 * </p>
 *
 * @author jibl
 * @since 2021-02-01
 */
public interface JobLogService extends IService<JobLog> {

    BaseResult selectJobLogInfos(HttpServletRequest request,JobLog jobLog);
}
