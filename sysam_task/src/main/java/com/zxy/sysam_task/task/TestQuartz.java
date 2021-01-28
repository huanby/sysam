package com.zxy.sysam_task.task;

import com.google.common.base.StandardSystemProperty;
import com.sun.swing.internal.plaf.synth.resources.synth_sv;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_task.config
 * @ClassName: TestQuartz
 * @Author: jibl
 * @Description:
 * @Date: 2021/1/25 11:10
 * @Version: 1.0
 */
@Component
public class TestQuartz implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //获取任务名
        String taskName = context.getJobDetail().getKey().getName();
        Map map = context.getTrigger().getJobDataMap();
        //处理执行任务之后的业务
        System.out.println(taskName + "测试定时器执行一次..................");
        //1.无参
        try {
            String methodName = map.get("methodName").toString();
            String params = map.get("params").toString();
            Method method = null;
            if(params.equals("")){
                method = this.getClass().getMethod(methodName);
                method.invoke(this);
            }else{
                method = this.getClass().getMethod(methodName,Class.forName("java.lang.String"));
                method.invoke(this, params);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void sayhello() {
        System.out.println("sayhello......................");
    }

    public void say(String value) {
        System.out.println(value);
    }
}

