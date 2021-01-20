package com.zxy.sysam_base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="User对象", description="")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    @TableId("id")
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("sex")
    private Integer sex;

    @TableField("phone")
    private String phone;

    @TableField("expired")
    private Integer expired;

    @TableField("disabled")
    private Integer disabled;

    @TableField("createdate")
    private Date createdate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
