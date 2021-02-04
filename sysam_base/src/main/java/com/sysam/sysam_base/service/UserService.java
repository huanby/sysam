package com.sysam.sysam_base.service;

import com.sysam.sysam_base.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sysam.sysam_base.wrapper.UserQueryWrapper;
import com.sysam.sysam_common.utils.PageUtil;
import com.sysam.sysam_common.utils.PageUtilResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jibl
 * @since 2021-01-19
 */
public interface UserService extends IService<User> {

    //分页获取用户列表
    PageUtilResult<User> userList(UserQueryWrapper userQueryWrapper, PageUtil pageUtil);
}
