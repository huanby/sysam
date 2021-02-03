package com.zxy.sysam_common.utils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * PageUtil.java
 * <p>
 * 分页工具类
 * currentPage  当前页数
 * pageSize     页面记录数
 *
 * @author hby
 * @since 2021-02-02
 */
public class PageUtil {


    @ApiModelProperty(value = "当前页数,该值必须大于等于1", example = "1")
    @Min(value = 0, message = "当前页数必须大于等于1")
    @NotNull(message = "当前页数不能为空")
    private Integer currentPage;


    @ApiModelProperty(value = "页面记录数，该值必须大于等于0", example = "10")
    @Min(value = 0, message = "页面记录数必须大于等于0")
    @NotNull(message = "页面记录数不能为空")
    private Integer pageSize;


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
