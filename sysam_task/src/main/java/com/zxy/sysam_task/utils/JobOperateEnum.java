package com.zxy.sysam_task.utils;

import java.io.Serializable;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_task.utils
 * @ClassName: JobOperateEnum
 * @Author: jibl
 * @Description:
 * @Date: 2021/1/25 9:29
 * @Version: 1.0
 */
public enum JobOperateEnum {
    START(0, "启动"),
    PAUSE(1, "暂停"),
    DELETE(2, "删除");

    private final Integer value;
    private final String desc;

    JobOperateEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Serializable getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getEnumName() {
        return name();
    }
}