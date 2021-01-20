package com.zxy.sysam_base.entity;

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
@TableName(value = "sys_role_menu", resultMap = "roleMenuMap")
@ApiModel(value = "RoleMenu对象", description = "")
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色菜单关联主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色id")
    @TableField("roleId")
    private Integer roleId;

    @ApiModelProperty(value = "菜单id")
    @TableField("menuId")
    private Integer menuId;

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
    private Integer describes;


    /**
     * 关联菜单信息
     */
    @ApiModelProperty(value = "关联菜单信息")
    @TableField(exist = false)
    private Menu menu;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
