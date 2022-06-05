package com.sysam.sysam_base.utils;

import com.sysam.sysam_base.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author huanyi
 * @version 1.0.0
 * @className LoginUserUtil
 * @description 登录用户工具类
 * @date 2022-05-31
 */
public class LoginUserUtil {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static User getLoginUser() {
        User lgoinUser = null;
        try {
            lgoinUser = SecurityUtils.getSubject().getPrincipal() != null ?
                    (User) SecurityUtils.getSubject().getPrincipal() : null;
        } catch (Exception e) {
            lgoinUser = null;
        }
        return lgoinUser;
    }
}
