package com.zxy.sysam_task.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 调度日志表
 * </p>
 *
 * @author jibl
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_job_log")
@ApiModel(value="JobLog对象", description="调度日志表")
public class JobLog extends Model<JobLog> {

    private static final long serialVersionUID=1L;


    /**
     * 任务执行成功
     */
    public static final String JOB_SUCCESS = "0";
    /**
     * 任务执行失败
     */
    public static final String JOB_FAIL = "1";

    @ApiModelProperty(value = "任务日志id")
    @TableId(value = "LOG_ID", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty(value = "任务id")
    @TableField("JOB_ID")
    private Long jobId;

    @ApiModelProperty(value = "spring bean名称")
    @TableField("BEAN_NAME")
    private String beanName;

    @ApiModelProperty(value = "方法名")
    @TableField("METHOD_NAME")
    private String methodName;

    @ApiModelProperty(value = "参数")
    @TableField("PARAMS")
    private String params;

    @ApiModelProperty(value = "任务状态    0：成功    1：失败")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "失败信息")
    @TableField("ERROR")
    private String error;

    @ApiModelProperty(value = "耗时(单位：毫秒)")
    @TableField("TIMES")
    private Long times;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

}
