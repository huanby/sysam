package com.zxy.sysam_task.service.impl;

import com.zxy.sysam_task.entity.Job;
import com.zxy.sysam_task.dao.JobMapper;
import com.zxy.sysam_task.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务表 服务实现类
 * </p>
 *
 * @author jibl
 * @since 2021-01-25
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

}
