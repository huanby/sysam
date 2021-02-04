package com.sysam.sysam_base.dao;

import com.sysam.sysam_base.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    //通过角色id查询角色信息
    Role getRoleById(Integer id);

}
