package com.sysam.sysam_base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

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
@TableName(value = "sys_role", resultMap = "roleMap")
@ApiModel(value = "Role对象", description = "")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色标识")
    @TableField("rolesign")
    private String rolesign;

    @ApiModelProperty(value = "角色名称")
    @TableField("rolename")
    private String rolename;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "启用状态  0-未启用 1已启用")
    @TableField("enable")
    private Integer enable;

    @ApiModelProperty(value = "创建者")
    @TableField("creator")
    private Integer creator;

    @ApiModelProperty(value = "创建时间")
    @TableField("createtime")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updatetime")
    private Date updatetime;

    @ApiModelProperty(value = "是否删除  0-未删除 1已删除")
    @TableField("isdel")
    private Integer isdel;

    @ApiModelProperty(value = "描述")
    @TableField("describes")
    private String describes;

    /**
     * 角色菜单关联信息
     */
    @ApiModelProperty(value = "角色菜单关联信息")
    private List<RoleMenu> roleMenus;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
