package com.zxy.sysam_base.dao;

import com.zxy.sysam_base.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hby
 * @since 2021-01-20
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    //通过用户id查询用户角色关联信息
    List<UserRole> getByUserId(Integer id);

}
