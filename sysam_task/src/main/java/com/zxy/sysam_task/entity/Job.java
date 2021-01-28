package com.zxy.sysam_task.entity;

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
 * 定时任务表
 * </p>
 *
 * @author jibl
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_job")
@ApiModel(value="Job对象", description="定时任务表")
public class Job extends Model<Job> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "任务id")
    @TableId(value = "JOB_ID", type = IdType.AUTO)
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

    @ApiModelProperty(value = "cron表达式")
    @TableField("CRON_EXPRESSION")
    private String cronExpression;

    @ApiModelProperty(value = "任务状态  0：正常  1：暂停")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.jobId;
    }

}
