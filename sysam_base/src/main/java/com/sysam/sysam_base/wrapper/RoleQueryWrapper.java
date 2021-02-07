/**
 *
 */
package com.sysam.sysam_base.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sysam.sysam_base.entity.Role;
import org.apache.commons.lang3.StringUtils;

/**
 * RoleQueryWrapper.java
 * @description Role角色实体查询包装类
 * @author hby
 * @since 2021-02-07
 *
 */
public class RoleQueryWrapper extends QueryWrapper<Role> {

    //角色id
    private Integer id;


    //角色标识
    private String rolesign;

    //角色名称
    private String rolename;

    //角色状态
    private Integer enable;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRolesign() {
        return rolesign;
    }

    public void setRolesign(String rolesign) {
        this.rolesign = rolesign;
        if (StringUtils.isNotBlank(this.rolesign)) {
            this.like(Role.ROLESIGN, this.rolesign);
        }
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
        if (StringUtils.isNotBlank(this.rolename)) {
            this.like(Role.ROLENAME, this.rolename);
        }
    }


    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
        if (this.enable != null) {
            this.eq(Role.ENABLE, this.enable);
        }
    }
}
