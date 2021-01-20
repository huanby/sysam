package com.zxy.sysam_base.service.impl;

import com.zxy.sysam_base.entity.User;
import com.zxy.sysam_base.dao.UserMapper;
import com.zxy.sysam_base.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jibl
 * @since 2021-01-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
