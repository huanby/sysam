package com.sysam.sysam_base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_user_role", resultMap = "userRoleMap")
@ApiModel(value = "UserRole对象", description = "")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户角色关联主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    @TableField("roleId")
    private Integer roleId;

    @ApiModelProperty(value = "创建时间")
    @TableField("createtime")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updatetime")
    private Date updatetime;

    @ApiModelProperty(value = "是否删除")
    @TableField("isdel")
    private Integer isdel;

    @ApiModelProperty(value = "描述")
    @TableField("describes")
    private String describes;


    /**
     * 关联角色信息
     */
    @ApiModelProperty(value = "关联角色信息")
    private Role role;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
