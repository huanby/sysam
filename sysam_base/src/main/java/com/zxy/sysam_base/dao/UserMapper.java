package com.zxy.sysam_base.dao;

import com.zxy.sysam_base.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jibl
 * @since 2021-01-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
