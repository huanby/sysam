package com.sysam.sysam_task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sysam.sysam_task.entity.JobLog;
import com.sysam.sysam_task.dao.JobLogMapper;
import com.sysam.sysam_task.entity.ScheduleJob;
import com.sysam.sysam_task.service.JobLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sysam.sysam_task.utils.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 调度日志表 服务实现类
 * </p>
 *
 * @author jibl
 * @since 2021-02-01
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements JobLogService {

    @Autowired
    JobLogMapper jobLogMapper;

    @Override
    public BaseResult selectJobLogInfos(HttpServletRequest request,JobLog jobLog) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        IPage<JobLog> jobLogPage = new Page<>(currentPage, pageSize);//参数一是当前页，参数二是每页个数
        QueryWrapper<JobLog> queryWrapper = new QueryWrapper<JobLog>();
        if (StringUtils.isNotBlank(jobLog.getBeanName())) {
            queryWrapper.like("BEAN_NAME", jobLog.getBeanName());
        }
        if (StringUtils.isNotBlank(jobLog.getMethodName())) {
            queryWrapper.like("METHOD_NAME", jobLog.getMethodName());
        }
        if (StringUtils.isNotBlank(jobLog.getStatus())) {
            queryWrapper.eq("STATUS", jobLog.getStatus());
        }
        queryWrapper.orderByDesc("CREATE_TIME");
        jobLogPage = jobLogMapper.selectPage(jobLogPage, queryWrapper);
        return new BaseResult(jobLogPage);
    }


}
