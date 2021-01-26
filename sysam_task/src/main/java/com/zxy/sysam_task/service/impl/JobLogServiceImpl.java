package com.zxy.sysam_task.service.impl;

import com.zxy.sysam_task.entity.JobLog;
import com.zxy.sysam_task.dao.JobLogMapper;
import com.zxy.sysam_task.service.JobLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 调度日志表 服务实现类
 * </p>
 *
 * @author jibl
 * @since 2021-01-26
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements JobLogService {

}
