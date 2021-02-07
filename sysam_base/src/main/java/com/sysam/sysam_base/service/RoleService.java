package com.sysam.sysam_base.service;

import com.sysam.sysam_base.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sysam.sysam_base.wrapper.RoleQueryWrapper;
import com.sysam.sysam_base.wrapper.UserQueryWrapper;
import com.sysam.sysam_common.utils.PageUtil;
import com.sysam.sysam_common.utils.PageUtilResult;

/**
 * <p>
 * 角色管理
 * 服务类
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
public interface RoleService extends IService<Role> {

    //分页获取角色列表
    PageUtilResult<Role> roleList(RoleQueryWrapper roleQueryWrapper, PageUtil pageUtil);

    //新增角色
    boolean roleAdd(Role role);

    //修改角色信息
    boolean roleUpdate(Role role);

    //通过角色ID删除角色信息
    boolean roleDelete(Integer id);
}
