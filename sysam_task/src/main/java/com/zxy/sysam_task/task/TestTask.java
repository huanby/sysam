package com.zxy.sysam_task.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_task.task
 * @ClassName: TestTask
 * @Author: jibl
 * @Description:
 * @Date: 2021/1/29 16:11
 * @Version: 1.0
 */
@Slf4j
@Component
public class TestTask {

    public void test(String params) {
        log.info("我是带参数的test方法，正在被执行，参数为：{}" , params);
    }
    public void test1() {
        log.info("我是不带参数的test1方法，正在被执行");
    }
    public void sayhello() {
        System.out.println("sayhello......................");
        System.out.println("==================执行完毕===============");
    }

    public void say(String value) {
        System.out.println(value);
        System.out.println("====================执行完毕===============");
    }

}
