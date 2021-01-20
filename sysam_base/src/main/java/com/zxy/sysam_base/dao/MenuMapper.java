package com.zxy.sysam_base.dao;

import com.zxy.sysam_base.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    //通过菜单id查询菜单信息
    Menu getMenuById();


}
