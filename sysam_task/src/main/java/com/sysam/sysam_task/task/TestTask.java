package com.sysam.sysam_task.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: sysam
 * @Package: com.sysam.sysam_task.task
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
    public void test2() {
        log.info("我是不带参数的test2方法，正在被执行");
    }
    public void test3() {
        log.info("我是不带参数的test3方法，正在被执行");
    }
    public void test4(String params) {
        log.info("我是带参数的test4方法，正在被执行，参数为：{}" , params);
    }
    public void test5(String params) {
        log.info("我是带参数的test5方法，正在被执行，参数为：{}" , params);
    }

}
