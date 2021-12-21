package com.sysam.sysam_task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sysam.sysam_task.entity.JobLog;
import com.sysam.sysam_task.utils.BaseResult;

import javax.servlet.http.HttpServletRequest;


/**
 * The interface Job log service.
 *
 * @author jibl
 * @date 2021 -12-21 20:47:49
 */
public interface JobLogService extends IService<JobLog> {


    /**
     * Select job log infos base result.
     *
     * @param request the request
     * @param jobLog  the job log
     * @return the base result
     * @author jibl
     * @date 2021 -12-21 20:47:49
     */
    BaseResult selectJobLogInfos(HttpServletRequest request,JobLog jobLog);
}
