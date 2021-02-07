package com.sysam.sysam_base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sysam.sysam_base.entity.Role;
import com.sysam.sysam_base.dao.RoleMapper;
import com.sysam.sysam_base.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sysam.sysam_base.wrapper.RoleQueryWrapper;
import com.sysam.sysam_common.utils.PageUtil;
import com.sysam.sysam_common.utils.PageUtilResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色管理
 * 服务实现类
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 分页获取角色列表
     *
     * @param roleQueryWrapper
     * @param pageUtil
     * @return
     */
    @Override
    public PageUtilResult<Role> roleList(RoleQueryWrapper roleQueryWrapper, PageUtil pageUtil) {
        //初始化查询条件
        IPage<Role> page = this.page(new Page<>(pageUtil.getCurrentPage(), pageUtil.getPageSize()), roleQueryWrapper);
        //result 返回数据
        PageUtilResult<Role> result = new PageUtilResult<>();
        //返回结果
        result.setRows(page.getRecords());
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @Override
    public boolean roleAdd(Role role) {
        //新增角色信息
        return this.save(role);
    }

    /**
     * 修改角色信息
     *
     * @param role
     * @return
     */
    @Override
    public boolean roleUpdate(Role role) {
        //修改角色信息
        return this.updateById(role);
    }

    /**
     * 通过角色ID删除角色信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean roleDelete(Integer id) {
        //通过角色ID删除角色信息
        return this.removeById(id);
    }
}
