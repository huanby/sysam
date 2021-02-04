package com.sysam.sysam_base.service.impl;

import com.sysam.sysam_base.entity.Menu;
import com.sysam.sysam_base.dao.MenuMapper;
import com.sysam.sysam_base.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
