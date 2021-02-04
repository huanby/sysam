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
 * 系统菜单表
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="系统菜单表")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    @TableField("menuname")
    private String menuname;

    @ApiModelProperty(value = "菜单url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "父菜单id")
    @TableField("pid")
    private Integer pid;

    @ApiModelProperty(value = "授权（多个用逗号分隔，如：user:list,user:add）")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "路由信息")
    @TableField("route")
    private String route;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "排序")
    @TableField("orderNum")
    private Integer orderNum;

    @ApiModelProperty(value = "启用状态  0-未启用 1已启用")
    @TableField("enable")
    private Integer enable;

    @ApiModelProperty(value = "是否删除  0-未删除 1已删除")
    @TableField("isdel")
    private Integer isdel;

    @ApiModelProperty(value = "创建者")
    @TableField("creator")
    private Integer creator;

    @ApiModelProperty(value = "更新者")
    @TableField("updateBy")
    private Integer updateBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("createtime")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updatetime")
    private Date updatetime;

    @ApiModelProperty(value = "描述")
    @TableField("describes")
    private String describes;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
