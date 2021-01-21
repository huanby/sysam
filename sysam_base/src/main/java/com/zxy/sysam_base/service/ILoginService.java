package com.zxy.sysam_base.service;

import com.zxy.sysam_base.entity.User;


/**
 * ILoginService.java
 * 用户登录服务接口
 *
 * @author hby
 * @since 2021-01-20
 */
public interface ILoginService {


    /**
     * 通过用户姓名获取用户信息
     * @param name
     * @return
     */
    User getUserByName(String name);

}
