package com.zxy.sysam_task.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_task.config
 * @ClassName: TaskJobFactory
 * @Author: jibl
 * @Description:
 * @Date: 2021/1/25 11:05
 * @Version: 1.0
 */
@Component
public class TaskJobFactory extends AdaptableJobFactory {
    @Autowired
    AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}

