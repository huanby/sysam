package com.zxy.sysam_base.dao;

import com.zxy.sysam_base.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    //通过角色id查询关联角色菜单信息
    List<RoleMenu> getByRoleId(Integer id);

}
