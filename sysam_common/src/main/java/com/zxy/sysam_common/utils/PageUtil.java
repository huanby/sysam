package com.zxy.sysam_common.utils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * PageUtil.java
 *
 * @description 分页工具类
 * offset 当前记录数
 * limit 显示记录数
 * @since 2021-01-29
 */
public class PageUtil {

    /**
     * 当前所在条数
     */
//	@ApiModelProperty(value = "分页当前记录位置,该值必须大于等于0",example = "0")
    @Min(value = 0, message = "当前记录数必须大于等于0")
    @NotNull(message = "所在条数不能为空")
    private Integer offset;

    /**
     * 总显示条数
     */
//	@ApiModelProperty(value = "一共显示多少条记录，该值必须大于0",example = "10")
    @Min(value = 1, message = "显示记录数必须大于0")
    @NotNull(message = "显示条数不能为空")
    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    //处理offset为当前查询页数，在mybatis-plus中使用
    public void setOffset(Integer offset) {
        this.offset = (offset / this.limit) + 1;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


}
